package com.hopoong.elasticsearch.api.todo.controller;

import com.hopoong.elasticsearch.api.todo.model.TodoModel;
import com.hopoong.elasticsearch.api.todo.service.TodoService;
import com.hopoong.elasticsearch.document.TodoDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<TodoModel.InfoModel> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public TodoModel.InfoModel getTodoById(@PathVariable String id) {
        return todoService.getTodoById(id);
    }

    @PostMapping
    public TodoDocument createTodo(@RequestBody TodoModel.RegisterModel todoDocument) {
        return todoService.createTodo(todoDocument);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDocument> updateTodo(@PathVariable String id, @RequestBody TodoModel.updateModel todoDocumentDetails) {
        TodoDocument updatedTodoDocument = todoService.updateTodo(todoDocumentDetails);
        return ResponseEntity.ok(updatedTodoDocument);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
