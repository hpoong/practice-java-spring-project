package com.hopoong.reactor.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponseCodeEnum {

    REACTOR("REACTOR", "");

    private final String type;
    private final String code;
}
