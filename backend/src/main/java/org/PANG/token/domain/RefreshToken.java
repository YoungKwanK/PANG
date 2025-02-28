package org.PANG.token.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RefreshToken {

    private final Long id;
    private final Long userId;
    private String refreshToken;
}
