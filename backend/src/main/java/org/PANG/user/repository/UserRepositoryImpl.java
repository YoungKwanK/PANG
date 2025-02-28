package org.PANG.user.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.PANG.user.application.interfaces.UserRepository;
import org.PANG.user.domain.User;
import org.PANG.user.repository.entity.UserEntity;
import org.PANG.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User findById(Long id) {
        UserEntity entity = jpaUserRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new);

        return entity.toUser();
    }


    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        entity = jpaUserRepository.save(entity);

        return entity.toUser();
    }

    @Override
    public boolean isUserExists(String email) {
        Optional<UserEntity> entity = jpaUserRepository.findByEmail(email);

        return entity.isPresent();
    }

    @Override
    public User findByEmail(String email) {
        UserEntity entity = jpaUserRepository.findByEmail(email)
            .orElseThrow(IllegalAccessError::new);

        return entity.toUser();
    }
}
