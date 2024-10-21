package com.hopoong.user_service.controller;

import com.hopoong.core_service.response.CommonResponseCodeEnum;
import com.hopoong.core_service.response.SuccessResponse;
import com.hopoong.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public SuccessResponse loadUserList() {
        return new SuccessResponse(CommonResponseCodeEnum.C01, userService.loadUserList());
    }
}
