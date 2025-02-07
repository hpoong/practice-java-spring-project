package com.hopoong.coreservice.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

public class MessageModel {

    @Data
    @SuperBuilder
    @NoArgsConstructor
    public static class BaseMessageModel {
        private String systemType;

    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    public static class RabbitMessagePublisherModel extends BaseMessageModel {
        private String queueType;
        private Long userId;
    }
}
