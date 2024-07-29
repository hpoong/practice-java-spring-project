package com.hopoong.reactor.controller;

import com.hopoong.reactor.model.TodoModel;
import com.hopoong.reactor.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/all-todos")
    public Flux<TodoModel> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public Mono<TodoModel> getTodoById(@PathVariable String id) {
        return todoService.getTodoById(id);
    }

    @PostMapping
    public Mono<TodoModel> createTodo() {
        return todoService.createTodo();
    }


    @PutMapping("/{id}")
    public Mono<TodoModel> updateTodo(@PathVariable String id) {
        return todoService.updateTodo(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTodo(@PathVariable String id) {
        return todoService.deleteTodoById(id);
    }

}
