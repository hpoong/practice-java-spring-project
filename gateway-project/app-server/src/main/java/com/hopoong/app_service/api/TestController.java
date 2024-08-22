package com.hopoong.app_service.api;

import com.hopoong.app_service.response.CommonResponseCodeEnum;
import com.hopoong.app_service.response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public SuccessResponse test() {
        return new SuccessResponse(CommonResponseCodeEnum.C01, "tests");
    }
}
