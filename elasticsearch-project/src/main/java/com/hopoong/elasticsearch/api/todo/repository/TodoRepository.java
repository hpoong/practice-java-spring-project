package com.hopoong.elasticsearch.api.todo.repository;

import com.hopoong.elasticsearch.document.TodoDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends ElasticsearchRepository<TodoDocument, String> {
}