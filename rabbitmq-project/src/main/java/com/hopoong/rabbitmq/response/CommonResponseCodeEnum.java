package com.hopoong.rabbitmq.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponseCodeEnum {

    PRODUCER("Producer", "");

    private final String type;
    private final String code;
}
