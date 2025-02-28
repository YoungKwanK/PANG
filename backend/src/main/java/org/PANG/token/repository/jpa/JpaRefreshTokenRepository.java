package org.PANG.token.repository.jpa;

import java.util.Optional;
import org.PANG.token.repository.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaRefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    @Modifying
    @Query(value = "UPDATE RefreshTokenEntity t "
        + "SET t.refreshToken = :#{#refreshTokenEntity.getRefreshToken()} "
        + "WHERE t.id = :#{#refreshTokenEntity.getId()}"
    )
    void updateRefreshTokenEntity(RefreshTokenEntity refreshTokenEntity);

    Optional<RefreshTokenEntity> findByUserId(Long userId);

    Optional<RefreshTokenEntity> findByRefreshToken(String RefreshToken);
}
