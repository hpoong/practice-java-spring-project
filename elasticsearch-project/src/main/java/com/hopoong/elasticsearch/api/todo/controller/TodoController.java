package com.hopoong.elasticsearch.api.todo.controller;

import com.hopoong.elasticsearch.api.todo.model.TodoModel;
import com.hopoong.elasticsearch.api.todo.service.TodoService;
import com.hopoong.elasticsearch.document.TodoDocument;
import com.hopoong.elasticsearch.response.CommonResponseCodeEnum;
import com.hopoong.elasticsearch.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


    @GetMapping
    public ResponseEntity<SuccessResponse> getAllTodos() {
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.SERVER, todoService.getAllTodos()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getTodoById(@PathVariable String id) {
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.SERVER, todoService.getTodoById(id)));
    }


    @PostMapping
    public ResponseEntity<SuccessResponse> createTodo(@RequestBody TodoModel.RegisterModel todoDocument) {
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.SERVER, todoService.createTodo(todoDocument)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateTodo(@RequestBody TodoModel.updateModel todoDocumentDetails) {
        todoService.updateTodo(todoDocumentDetails);
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.SERVER, "update success"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.SERVER, "delete success"));
    }
}
