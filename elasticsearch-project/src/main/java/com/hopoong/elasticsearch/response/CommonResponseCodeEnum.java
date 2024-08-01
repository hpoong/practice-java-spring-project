package com.hopoong.elasticsearch.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponseCodeEnum {

    SERVER("", "C01");

    private final String type;
    private final String code;
}
