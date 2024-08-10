package com.hopoong.elasticsearch.document;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "todo")
public class TodoDocument {

    @Id
    private String id;
    private String title;
    private String description;
    private boolean completed;

    @Builder
    public TodoDocument(String id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

}