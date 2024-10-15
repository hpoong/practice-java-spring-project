package com.hopoong.user_service.service;

import com.hopoong.core_service.repository.UsersJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersJpaRepository usersJpaRepository;

    public String loadUserList() {
        return null;
    }
}
