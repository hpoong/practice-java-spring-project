package com.hopoong.rabbitmq.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hopoong.rabbitmq.model.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    private final DirectExchange directExchange;
//
//    private final FanoutExchange fanoutExchange;
//
//    private final HeadersExchange headersExchange;
//
//    private final TopicExchange topicExchange;


    @Override
    public void directSendMessage(MessageDto messageDto) {
        try {
            String objectToJSON = objectMapper.writeValueAsString(messageDto);
            rabbitTemplate.convertAndSend(directExchange.getName(), "routingKey1", objectToJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void fanoutSendMessage(MessageDto messageDto) {
//        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", messageDto);
//    }
//
//    @Override
//    public void headerSendMessage(MessageDto messageDto) {
//        rabbitTemplate.convertAndSend(headersExchange.getName(), "", messageDto, message -> {
//            message.getMessageProperties().getHeaders().put("headerKey", "headerValue");
//            return message;
//        });
//    }
//
//    @Override
//    public void topicSendMessage(MessageDto messageDto) {
//        rabbitTemplate.convertAndSend(topicExchange.getName(), "topic.routing.specific", messageDto);
//    }
}
