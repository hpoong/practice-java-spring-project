package com.hopoong.user_service.service;

import com.hopoong.core_service.entity.UsersEntity;
import com.hopoong.core_service.repository.UsersJpaRepository;
import com.hopoong.user_service.model.UsersModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersJpaRepository usersJpaRepository;

    public List<UsersModel> loadUserList() {
        return usersJpaRepository.findAll()
                .stream().map(this::convertToModel).collect(Collectors.toList());
    }

    private UsersModel convertToModel(UsersEntity entity) {
        return UsersModel.builder()
                .name(entity.getName())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .build();
    }
}
