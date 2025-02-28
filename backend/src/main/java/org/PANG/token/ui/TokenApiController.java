package org.PANG.token.ui;

import lombok.RequiredArgsConstructor;
import org.PANG.token.application.TokenService;
import org.PANG.token.application.dto.CreateAccessTokenRequestDto;
import org.PANG.token.ui.dto.CreateAccessTokenResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponseDto> createNewAccessToken(
        @RequestBody CreateAccessTokenRequestDto dto) {
        String newAccessToken = tokenService.createNewAccessToken(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CreateAccessTokenResponseDto(newAccessToken));
    }
}