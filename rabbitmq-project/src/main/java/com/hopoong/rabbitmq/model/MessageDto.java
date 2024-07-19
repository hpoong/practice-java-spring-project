package com.hopoong.rabbitmq.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MessageDto {
    private String title;
    private String message;

    @Builder
    public MessageDto(String title, String message) {
        this.title = title;
        this.message = message;
    }
}
