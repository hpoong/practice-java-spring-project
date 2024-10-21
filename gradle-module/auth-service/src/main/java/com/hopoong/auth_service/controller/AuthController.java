package com.hopoong.auth_service.controller;

import com.hopoong.auth_service.service.AuthService;
import com.hopoong.core_service.response.CommonResponseCodeEnum;
import com.hopoong.core_service.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public SuccessResponse login(@RequestParam Long userId) {
        return new SuccessResponse(CommonResponseCodeEnum.C01, authService.login(userId));
    }
}
