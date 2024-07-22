package com.hopoong.rabbitmq.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hopoong.rabbitmq.model.MessageDto;
import com.hopoong.rabbitmq.response.CommonResponseCodeEnum;
import com.hopoong.rabbitmq.response.SuccessResponse;
import com.hopoong.rabbitmq.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/producer")
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping("/send/direct-queue")
    public ResponseEntity<?> sendDirectSendMessage() throws JsonProcessingException {
        producerService.directSendMessage(new MessageDto());
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.PRODUCER, "success"));
    }

    @GetMapping("/send/fanout-queue")
    public ResponseEntity<?> sendFanoutSendMessage() throws JsonProcessingException {
        producerService.fanoutSendMessage(new MessageDto());
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.PRODUCER, "success"));
    }

    @GetMapping("/send/headers-queue1")
    public ResponseEntity<?> sendHeadersSendMessage1() throws JsonProcessingException {
        producerService.headerSendMessage(new MessageDto(), "headerKey", "headerValue", "headerKey1", "headerValue1");
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.PRODUCER, "success"));
    }

    @GetMapping("/send/headers-queue2")
    public ResponseEntity<?> sendHeadersSendMessage2() throws JsonProcessingException {
        producerService.headerSendMessage(new MessageDto(), "headerKey1", "headerValue1");
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.PRODUCER, "success"));
    }

    @GetMapping("/send/headers-queue3")
    public ResponseEntity<?> sendHeadersSendMessage3() throws JsonProcessingException {
        producerService.headerSendMessage(new MessageDto(), "headerKey3", "headerValue3", "headerKey4", "headerValue4", "headerKey5", "headerValue5");
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.PRODUCER, "success"));
    }

    @GetMapping("/send/topic-queue")
    public ResponseEntity<?> sendTopicSendMessage() throws JsonProcessingException {
        producerService.topicSendMessage(new MessageDto());
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.PRODUCER, "success"));
    }

}
