package com.hopoong.userservice.api;

import com.hopoong.userservice.response.CommonResponseCodeEnum;
import com.hopoong.userservice.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public SuccessResponse register(@RequestBody UserModel.RegisterUserModel registerUserModel) {
        service.register(registerUserModel);
        return new SuccessResponse(CommonResponseCodeEnum.C01, "user register success");
    }

    @PostMapping("/reset-password/{userId}")
    public SuccessResponse resetPassword(@PathVariable(name = "userId") Long userId) {
        service.resetPassword(userId);
        return new SuccessResponse(CommonResponseCodeEnum.C01, "user resetPassword success");
    }
}
