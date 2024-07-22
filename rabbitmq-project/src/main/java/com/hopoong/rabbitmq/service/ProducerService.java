package com.hopoong.rabbitmq.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hopoong.rabbitmq.model.MessageDto;

public interface ProducerService {
    void directSendMessage(MessageDto messageDto) throws JsonProcessingException;      // Direct Exchange 방식 이용

    void fanoutSendMessage(MessageDto messageDto) throws JsonProcessingException;      // Fanout Exchange 방식 이용

    void headerSendMessage(MessageDto messageDto) throws JsonProcessingException;      // Header Exchange 방식 이용

    void headerSendMessage(MessageDto messageDto, Object... headers) throws JsonProcessingException;

    void topicSendMessage(MessageDto messageDto) throws JsonProcessingException;       // Topic Exchange 방식 이용
}
