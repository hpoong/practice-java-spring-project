package com.hopoong.emailservice.listener;

import com.hopoong.coreservice.model.MessageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;


@Component
public class MessageUserListener {

//    // 해더가 없는 경우 - 메세지 단순 consume
//    @Bean
//    public Consumer<MessageModel.RabbitMessagePublisherModel> consumeUserEmail() {
//        return message -> {
//
//            switch (message.getQueueType()) {
//                case "registered" -> System.out.println("📧 email-service registered :::: " + message.toString());
//                case "password-changed" -> System.out.println("📧 email-service password-changed :::: " + message.toString());
//                default ->  throw new RuntimeException("강제 예외 발생: DLQ 테스트");
//            }
//        };
//    }

    // 헤더 추가
    @Bean
    public Consumer<Message<MessageModel.RabbitMessagePublisherModel>> consumeUserEmail() {
        return message -> {
            String eventType = message.getHeaders().get("eventType", String.class);
            switch (eventType) {
                case "registered" -> System.out.println("📧 email-service registered :::: " + message.getPayload().toString());
                case "password-changed" -> System.out.println("📧 email-service password-changed :::: " + message.getPayload().toString());
//                case "password-changed" -> throw new RuntimeException("강제 예외 발생: DLQ 테스트");
                default ->  throw new RuntimeException("강제 예외 발생: DLQ 테스트");
            }
        };
    }

}
