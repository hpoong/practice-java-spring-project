package com.hopoong.auth_service.service;

import com.hopoong.auth_service.exception.UserNotFoundException;
import com.hopoong.auth_service.model.UserModel;
import com.hopoong.auth_service.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    @Transactional(readOnly = true)
    public UserModel login(Long userId) {
        return Optional.ofNullable(authRepository.findUsersEntityById(userId))
                .orElseThrow(() -> {
                    log.error("Login failed for user ID: {}", userId);
                    return new UserNotFoundException("User not found with ID: " + userId);
                });
    }
}
