package org.PANG.controller;

import org.PANG.config.oauth.GoogleOAuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class GoogleAuthController {

    private  final GoogleOAuthProperties googleOAuthProperties;

    @Autowired  // 생성자 주입
    public GoogleAuthController(GoogleOAuthProperties googleOAuthProperties) {
        this.googleOAuthProperties = googleOAuthProperties;
    }
    private final String GOOGLE_TOKEN_URL = "https://oauth2.googleapis.com/token";
    private final String REDIRECT_URI = "postmessage"; // React에서 auth-code 방식 사용 시

    @PostMapping("/google")
    public ResponseEntity<?> googleAuth(@RequestBody Map<String, String> request) {
        System.out.println(request);
        String authCode = request.get("code");

        // Google에 Access Token 요청
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String GOOGLE_CLIENT_ID=googleOAuthProperties.getClientId();
        String GOOGLE_CLIENT_SECRET=googleOAuthProperties.getClientSecret();

        String requestBody = String.format(
                "code=%s&client_id=%s&client_secret=%s&redirect_uri=%s&grant_type=authorization_code",
                authCode, GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET, REDIRECT_URI
        );

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(GOOGLE_TOKEN_URL, HttpMethod.POST, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> tokens = response.getBody();
            return ResponseEntity.ok(tokens);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Google token request failed");
        }
    }
}

