package com.hopoong.userservice.domain;

import com.hopoong.userservice.api.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    // 이메일 인증 여부
    @Column(nullable = false)
    private boolean verified = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();


    public User toEntity(UserModel.RegisterUserModel registerUserModel) {
        return User.builder()
                .email(registerUserModel.getEmail())
                .name(registerUserModel.getName())
                .password(registerUserModel.getPassword())
                .verified(false)
                .createdAt(LocalDateTime.now())
                .build();
    }
}


