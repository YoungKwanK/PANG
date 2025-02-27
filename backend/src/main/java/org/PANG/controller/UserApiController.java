package org.PANG.controller;

import lombok.RequiredArgsConstructor;
import org.PANG.domain.User;
import org.PANG.dto.AddUserRequest;
import org.PANG.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody AddUserRequest addUserRequest) {
        Long id = userService.save(addUserRequest);
        return ResponseEntity.ok(200);
    }
}
