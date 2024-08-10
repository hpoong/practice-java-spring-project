package com.hopoong.elasticsearch.api.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoModel {


    public TodoModel() {}

    private String id;
    private String title;
    private String description;
    private boolean completed;

    @Data
    public static class RegisterModel extends TodoModel { }


    @Data
    public static class updateModel extends TodoModel { }

    @Data
    public static class InfoModel extends TodoModel {
        public InfoModel(String id, String title, String description, boolean completed) {
            super(id, title, description, completed);
        }
    }


}
