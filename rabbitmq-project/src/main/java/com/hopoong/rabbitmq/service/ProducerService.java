package com.hopoong.rabbitmq.service;

import com.hopoong.rabbitmq.model.MessageDto;

public interface ProducerService {
    void directSendMessage(MessageDto messageDto);      // Direct Exchange 방식 이용

//    void fanoutSendMessage(MessageDto messageDto);      // Fanout Exchange 방식 이용
//
//    void headerSendMessage(MessageDto messageDto);      // Header Exchange 방식 이용
//
//    void topicSendMessage(MessageDto messageDto);       // Topic Exchange 방식 이용
}
