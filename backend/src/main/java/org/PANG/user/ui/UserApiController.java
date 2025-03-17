package org.PANG.user.ui;

import lombok.RequiredArgsConstructor;
import org.PANG.user.application.UserService;
import org.PANG.user.application.dto.CreateUserRequestDto;
import org.PANG.user.domain.User;
import org.PANG.user.ui.dto.CreateUserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user/sign-up")
    public ResponseEntity<CreateUserResponseDto> signUp(@RequestBody CreateUserRequestDto dto) {
        System.out.println(dto);
        User user = userService.save(dto);

        return ResponseEntity.ok().body(new CreateUserResponseDto(user.getId()));
    }
}
