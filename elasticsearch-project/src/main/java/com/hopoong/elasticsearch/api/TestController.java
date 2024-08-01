package com.hopoong.elasticsearch.api;

import com.hopoong.elasticsearch.response.CommonResponseCodeEnum;
import com.hopoong.elasticsearch.response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public SuccessResponse test() {
        return new SuccessResponse(CommonResponseCodeEnum.SERVER, "tests");
    }
}
