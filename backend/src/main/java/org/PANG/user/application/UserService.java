package org.PANG.user.application;

import lombok.RequiredArgsConstructor;
import org.PANG.user.application.dto.CreateUserRequestDto;
import org.PANG.user.application.interfaces.UserRepository;
import org.PANG.user.domain.User;
import org.PANG.user.domain.UserInfo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User save(CreateUserRequestDto dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        UserInfo userInfo = new UserInfo(dto.email(), dto.name(), dto.birth(),
            dto.residence());

        User user = new User(null, userInfo);

        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean isUserExists(String email) {
        return userRepository.isUserExists(email);
    }
}
