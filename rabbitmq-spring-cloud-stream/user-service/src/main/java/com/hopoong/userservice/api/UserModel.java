package com.hopoong.userservice.api;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserModel {


    @Data
    public static class RegisterUserModel {
        private String email;
        private String password;
        private String name;
    }







}
