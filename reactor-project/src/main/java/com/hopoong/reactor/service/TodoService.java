package com.hopoong.reactor.service;


import com.hopoong.reactor.model.TodoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final List<TodoModel> todoList = new ArrayList<>();

    public Flux<TodoModel> getAllTodos() {
        return Flux.fromIterable(todoList);
    }


    public Mono<TodoModel> getTodoById(String id) {
        return Mono.justOrEmpty(
                todoList.stream().filter(todo -> todo.getId().equals(id))
                        .findFirst()
                        .orElse(null)
        );
    }

    public Mono<TodoModel> createTodo() {
        String id = UUID.randomUUID().toString().substring(0, 5);
        todoList.add(new TodoModel(id, id+" - title", false));
        return null;
    }

    public Mono<TodoModel> updateTodo(String id) {
        String uuid = UUID.randomUUID().toString().substring(0, 5);
        return getTodoById(id).flatMap(todo -> {
            todo.setTitle(uuid + " - title");
            return Mono.just(todo);
        });
    }

    public Mono<Void> deleteTodoById(String id) {
        return getTodoById(id).flatMap(todo -> {
            todoList.remove(todo);
            return Mono.empty();
        });
    }

}
