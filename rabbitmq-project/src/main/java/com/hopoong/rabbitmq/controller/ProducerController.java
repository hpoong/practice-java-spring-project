package com.hopoong.rabbitmq.controller;

import com.hopoong.rabbitmq.model.MessageDto;
import com.hopoong.rabbitmq.response.CommonResponseCodeEnum;
import com.hopoong.rabbitmq.response.SuccessResponse;
import com.hopoong.rabbitmq.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/producer")
public class ProducerController {

    private final ProducerService producerService;


    /**
     * 생산자(Proceduer)가 메시지를 전송합니다.
     * RabbitMQ 내에 메시지를 큐에 저장
     */
    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto) {
        producerService.directSendMessage(messageDto);
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.PRODUCER, "success"));
    }


}
