package org.PANG.token.application.interfaces;

import org.PANG.token.domain.RefreshToken;

public interface RefreshTokenRepository {

    RefreshToken save(RefreshToken refreshToken);

    RefreshToken update(RefreshToken refreshToken);

    RefreshToken findByRefreshToken(String refreshToken);

    RefreshToken findByUserId(Long userId);
}
