package com.hopoong.rabbitmq.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {


    @RabbitListener(queues = "directQueue")
    public void receiveDirectQueueMessage(String msg) {
        log.info("directQueue 내의 결과 값을 반환 받습니다 ::::  {}", msg);
    }

    @RabbitListener(queues = "directQueue1")
    public void receiveDirectQueueMessage1(String msg) {
        log.info("directQueue1 내의 결과 값을 반환 받습니다 ::::  {}", msg);
    }

    @RabbitListener(queues = "fanoutQueue")
    public void receiveFanoutQueueMessage(String msg) {
        log.info("fanoutQueue 내의 결과 값을 반환 받습니다 ::::  {}", msg);
    }

    @RabbitListener(queues = "fanoutQueue1")
    public void receiveFanoutQueueMessage1(String msg) {
        log.info("fanoutQueue1 내의 결과 값을 반환 받습니다 ::::  {}", msg);
    }

    @RabbitListener(queues = "headersQueue1")
    public void receiveHeadersQueueMessage1(String msg) {
        log.info("headersQueue1 내의 결과 값을 반환 받습니다 ::::  {}", msg);
    }

    @RabbitListener(queues = "headersQueue2")
    public void receiveHeadersQueueMessage2(String msg) {
        log.info("headersQueue2 내의 결과 값을 반환 받습니다 ::::  {}", msg);
    }

    @RabbitListener(queues = "headersQueue3")
    public void receiveHeadersQueueMessage3(String msg) {
        log.info("headersQueue3 내의 결과 값을 반환 받습니다 ::::  {}", msg);
    }

    @RabbitListener(queues = "topicQueue")
    public void receiveTopicQueueMessage(String msg) {
        log.info("topicQueue 내의 결과 값을 반환 받습니다 ::::  {}", msg);
    }

}
