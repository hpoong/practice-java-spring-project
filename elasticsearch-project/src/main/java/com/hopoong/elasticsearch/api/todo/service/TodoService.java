package com.hopoong.elasticsearch.api.todo.service;

import com.hopoong.elasticsearch.api.todo.model.TodoModel;
import com.hopoong.elasticsearch.api.todo.repository.TodoRepository;
import com.hopoong.elasticsearch.document.TodoDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    /*
     * 모든 일정 목록 조회
     */
    public List<TodoModel.InfoModel> getAllTodos() {
        Iterable<TodoDocument> todoDocuments = todoRepository.findAll();

        List<TodoDocument> todoDocumentList = StreamSupport.stream(todoDocuments.spliterator(), false)
                .collect(Collectors.toList());

        return todoDocumentList.stream()
                .map(data -> new TodoModel.InfoModel(data.getId(), data.getTitle(), data.getDescription(), data.isCompleted()))
                .collect(Collectors.toList());
    }

    /*
     * 일정 조회
     */
    public TodoModel.InfoModel getTodoById(String id) {
        Optional<TodoDocument> todoDocument = todoRepository.findById(id);

        if(todoDocument.isEmpty())
            throw new IllegalArgumentException("empty");

        return new TodoModel.InfoModel(todoDocument.get().getId(), todoDocument.get().getTitle(), todoDocument.get().getDescription(), todoDocument.get().isCompleted());
    }

    /*
     * 일정 등록
     */
    public TodoDocument createTodo(TodoModel.RegisterModel param) {
        Optional<TodoDocument> existingTodo = todoRepository.findById(param.getId());

        if(existingTodo.isPresent())
            throw new IllegalArgumentException("already exists");

        TodoDocument todoDocument = TodoDocument.builder()
                .title(param.getTitle())
                .id(param.getId())
                .description(param.getDescription())
                .completed(param.isCompleted()).build();

        return todoRepository.save(todoDocument);
    }


    /*
     * 일정 변경
     */
    public TodoDocument updateTodo(TodoModel.updateModel param) {

        Optional<TodoDocument> todoDocumentOptional = todoRepository.findById(param.getId());

        if(todoDocumentOptional.isEmpty())
            throw new IllegalArgumentException("empty");

        TodoDocument todoDocument = todoDocumentOptional.get();
        todoDocument.setCompleted(param.isCompleted());
        todoDocument.setTitle(param.getTitle());
        todoDocument.setDescription(param.getDescription());
        return todoRepository.save(todoDocument);
    }

    /*
     * 일정 삭제
     */
    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }

}