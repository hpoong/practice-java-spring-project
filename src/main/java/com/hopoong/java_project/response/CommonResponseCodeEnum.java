package com.hopoong.java_project.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponseCodeEnum {

    // ************ T1 : **


    // ************ T9 : Server
    SERVER("", "C01");

    private final String type;
    private final String code;
}
