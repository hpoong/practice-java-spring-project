package com.hopoong.gateway_service.api;

import com.hopoong.gateway_service.response.CommonResponseCodeEnum;
import com.hopoong.gateway_service.response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("/get")
    public SuccessResponse test() {
        return new SuccessResponse(CommonResponseCodeEnum.C01, "tests");
    }
}
