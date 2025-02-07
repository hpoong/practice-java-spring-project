package com.hopoong.emailservice.listener;

import com.hopoong.coreservice.model.MessageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;


@Component
public class MessageUserListener {

    @Bean
    public Consumer<MessageModel.RabbitMessagePublisherModel> consumeUserEmail() {
        return message -> {

            switch (message.getQueueType()) {
                case "registered" -> System.out.println("📧 MessageUserListener registered received :::: " + message.toString());
                case "password-changed" -> System.out.println("📧 MessageUserListener password-changed :::: " + message.toString());
                default ->  System.out.println("⚠️ MessageUserListener Unrecognized event type: " + message.getSystemType());
            }
        };
    }

}
