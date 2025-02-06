package com.hopoong.userservice.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

public class DispatcherMessageModel {

    @Getter
    @Setter
    @SuperBuilder
    @ToString
    public static class DispatcherSystemType {
        private String systemType;
        private String queueType;
    }

    @Data
    @SuperBuilder
    @ToString(callSuper = true)
    public static class DispatcherRabbitmq extends DispatcherSystemType {
        private Long userId;
    }
}
