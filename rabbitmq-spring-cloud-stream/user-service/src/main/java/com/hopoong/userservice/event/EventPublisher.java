package com.hopoong.userservice.event;

import com.hopoong.coreservice.model.MessageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EventPublisher {


    private final StreamBridge streamBridge;
    private final String OUT_BOUND_TOPIC = "rabbit-binder-out";

    public void publishUserRegistered(Long userId) {
        sendEvent("registered", userId);
    }

    public void publishUserPasswordChanged(Long userId) {
        sendEvent("password-changed", userId);
    }

    public void publishUserOrderCompleted(Long userId) {
        sendEvent("order-completed", userId);
    }

    private void sendEvent(String eventType, Long userId) {
        MessageModel.RabbitMessagePublisherModel message =
            MessageModel.RabbitMessagePublisherModel.builder()
                .userId(userId)
                .systemType("rabbitmq")
                .queueType(eventType)
                .build();


        // 헤더 추가
        MessageHeaderAccessor accessor = new MessageHeaderAccessor();
        accessor.setHeader("eventType", eventType);

        Message<MessageModel.RabbitMessagePublisherModel> msg = MessageBuilder
                .withPayload(message)
                .setHeaders(accessor)
                .build();

//        streamBridge.send(OUT_BOUND_TOPIC, message); // 해더가 없는 경우 
        streamBridge.send(OUT_BOUND_TOPIC, msg); // 헤더 추가 경우
        System.out.println("send ::: " + message.toString());
    }


}
