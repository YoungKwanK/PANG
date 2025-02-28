package org.PANG.token.application;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.PANG.token.application.dto.CreateAccessTokenRequestDto;
import org.PANG.token.application.interfaces.RefreshTokenRepository;
import org.PANG.token.domain.TokenProvider;
import org.PANG.user.application.UserService;
import org.PANG.user.domain.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    //리프레시 토큰으로 유효성 검사 후 유효하면 사용자 ID를 찾아 새로운 엑세스 토큰 생성
    public String createNewAccessToken(CreateAccessTokenRequestDto dto) {
        String refreshToken = dto.refreshToken();

        // 토큰 유효성 검사에 실패하면 예외 발생
        if (!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenRepository.findByRefreshToken(refreshToken)
            .getUserId();
        User user = userService.getUser(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}

