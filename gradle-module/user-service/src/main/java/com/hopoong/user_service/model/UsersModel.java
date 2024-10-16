package com.hopoong.user_service.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UsersModel {
    private String name;
    private String password;
    private String email;
}

