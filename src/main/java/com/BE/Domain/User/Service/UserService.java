package com.BE.Domain.User.Service;

import com.BE.Domain.User.Entity.UserEntity;
import com.BE.Domain.User.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity registerUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean validateLogin(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    //외부 로그인 (지금은 x)
//    public Optional<UserEntity> findByLoginMethodAndExternalId(String loginMethod, String externalId) {
//        return userRepository.findByLoginMethodAndExternalId(loginMethod, externalId);
//    }
}
