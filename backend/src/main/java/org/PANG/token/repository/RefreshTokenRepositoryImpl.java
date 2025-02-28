package org.PANG.token.repository;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.PANG.token.application.interfaces.RefreshTokenRepository;
import org.PANG.token.domain.RefreshToken;
import org.PANG.token.repository.entity.RefreshTokenEntity;
import org.PANG.token.repository.jpa.JpaRefreshTokenRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;


    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenEntity entity = new RefreshTokenEntity(refreshToken);
        Long userId = entity.getUserId();

        Optional<RefreshTokenEntity> savedEntity = jpaRefreshTokenRepository.findByUserId(userId);

        if (savedEntity.isPresent()) {
            refreshToken = new RefreshToken(savedEntity.get().getId(), userId,
                refreshToken.getRefreshToken());
            jpaRefreshTokenRepository.updateRefreshTokenEntity(
                new RefreshTokenEntity(refreshToken));
            return refreshToken;
        }

        return jpaRefreshTokenRepository.save(entity).toRefreshToken();
    }

    @Override
    @Transactional
    public RefreshToken update(RefreshToken refreshToken) {
        RefreshTokenEntity entity = new RefreshTokenEntity(refreshToken);
        jpaRefreshTokenRepository.updateRefreshTokenEntity(entity);
        return entity.toRefreshToken();
    }

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        return jpaRefreshTokenRepository.findByRefreshToken(refreshToken)
            .orElseThrow().toRefreshToken();
    }

    @Override
    public RefreshToken findByUserId(Long userId) {
        return jpaRefreshTokenRepository.findByUserId(userId).orElseThrow().toRefreshToken();
    }
}
