package com.hopoong.reactor.controller;

import com.hopoong.reactor.response.CommonResponseCodeEnum;
import com.hopoong.reactor.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class DemoController {

    @GetMapping("/text")
    public ResponseEntity<?> getText() {
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.REACTOR, "success"));
    }
}
