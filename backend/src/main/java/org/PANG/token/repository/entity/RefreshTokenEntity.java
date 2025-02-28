package org.PANG.token.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.PANG.token.domain.RefreshToken;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "RefreshToken")
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    public RefreshTokenEntity(RefreshToken refreshToken) {
        this.id = refreshToken.getId();
        this.userId = refreshToken.getUserId();
        this.refreshToken = refreshToken.getRefreshToken();
    }

    public RefreshToken toRefreshToken() {
        return RefreshToken.builder()
            .id(id)
            .userId(userId)
            .refreshToken(refreshToken)
            .build();
    }
}
