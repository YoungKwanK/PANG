package org.PANG.user.application.interfaces;

import org.PANG.user.domain.User;

public interface UserRepository {

    User findById(Long id);

    User findByEmail(String email);

    User save(User user);

    boolean isUserExists(String email);
}
