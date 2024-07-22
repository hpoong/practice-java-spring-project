package com.hopoong.rabbitmq.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MessageDto {
    private String title = "title-test";
    private String message = "message-test";

    @Builder
    public MessageDto(String title, String message) {
        this.title = title;
        this.message = message;
    }
}
