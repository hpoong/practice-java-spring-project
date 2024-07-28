package com.hopoong.reactor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoModel {
    private String id;
    private String title;
    private boolean completed;
}
