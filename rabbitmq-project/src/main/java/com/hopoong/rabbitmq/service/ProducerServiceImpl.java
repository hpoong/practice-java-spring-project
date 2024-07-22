package com.hopoong.rabbitmq.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    private final FanoutExchange fanoutExchange;

    private final HeadersExchange headersExchange;

    private final TopicExchange topicExchange;


    @Override
    public void directSendMessage(MessageDto messageDto) throws JsonProcessingException {
        String objectToJSON = objectMapper.writeValueAsString(messageDto);
        rabbitTemplate.convertAndSend(directExchange.getName(), "routingKey1", objectToJSON);
    }

    @Override
    public void fanoutSendMessage(MessageDto messageDto) throws JsonProcessingException {
        String objectToJSON = objectMapper.writeValueAsString(messageDto);
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", objectToJSON);
    }

    @Override
    public void headerSendMessage(MessageDto messageDto) throws JsonProcessingException {
        String objectToJSON = objectMapper.writeValueAsString(messageDto);
        rabbitTemplate.convertAndSend(headersExchange.getName(), "", objectToJSON, message -> {
            message.getMessageProperties().getHeaders().put("headerKey", "headerValue");
            return message;
        });
    }

    @Override
    public void headerSendMessage(MessageDto messageDto, Object... headers) throws JsonProcessingException {
        String objectToJSON = objectMapper.writeValueAsString(messageDto);
        rabbitTemplate.convertAndSend(headersExchange.getName(), "", objectToJSON, message -> {
            for (int i = 0; i < headers.length; i += 2) {
                message.getMessageProperties().getHeaders().put(headers[i].toString(), headers[i + 1]);
            }
            return message;
        });
    }


    @Override
    public void topicSendMessage(MessageDto messageDto) throws JsonProcessingException {
        String objectToJSON = objectMapper.writeValueAsString(messageDto);
        rabbitTemplate.convertAndSend(topicExchange.getName(), "topic.routing.specific", objectToJSON);
    }
}
