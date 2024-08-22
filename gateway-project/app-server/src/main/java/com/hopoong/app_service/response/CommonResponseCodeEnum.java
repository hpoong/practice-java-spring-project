package com.hopoong.app_service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponseCodeEnum {

    C01("", "C01");

    private final String type;
    private final String code;
}