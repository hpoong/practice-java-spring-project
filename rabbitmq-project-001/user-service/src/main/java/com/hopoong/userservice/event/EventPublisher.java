package com.hopoong.userservice.event;

import com.hopoong.coreservice.model.MessageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

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

        streamBridge.send(OUT_BOUND_TOPIC, message);
        System.out.println("send ::: " + message.toString());
    }


}
