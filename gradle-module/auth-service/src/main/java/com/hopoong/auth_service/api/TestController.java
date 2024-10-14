package com.hopoong.auth_service.api;

import com.hopoong.auth_service.response.CommonResponseCodeEnum;
import com.hopoong.auth_service.response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public SuccessResponse test() {
        return new SuccessResponse(CommonResponseCodeEnum.C01, "tests");
    }
}
