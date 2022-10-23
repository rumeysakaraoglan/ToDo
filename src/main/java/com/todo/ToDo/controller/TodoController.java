package com.todo.ToDo.controller;

import com.todo.ToDo.dto.TodoAddRequestDto;
import com.todo.ToDo.dto.TodoResponseDto;
import com.todo.ToDo.entity.TodoList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.todo.ToDo.service.TodoService;
import java.util.List;

@RestController
@RequestMapping("user/todo")

public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/add")
    public ResponseEntity<TodoAddRequestDto> add(@RequestBody TodoList todoList) {
        return new ResponseEntity<TodoAddRequestDto>(todoService.add(todoList).getBody(), HttpStatus.OK);
    }

    @GetMapping("/week")
    public ResponseEntity<List<TodoResponseDto>> getWeek() {
        return new ResponseEntity<>(todoService.getWeek(), HttpStatus.OK);
    }

    @GetMapping("/month")
    public ResponseEntity<List<TodoResponseDto>> getMonth() {
        return new ResponseEntity<>(todoService.getMonth(), HttpStatus.OK);
    }

    @GetMapping("/day")
    public ResponseEntity<List<TodoResponseDto>> getDay() {
        return new ResponseEntity<>(todoService.getDay(), HttpStatus.OK);
    }

    @GetMapping("/notDone")
    public ResponseEntity<List<TodoResponseDto>> getNotDone() {
        return new ResponseEntity<>(todoService.getNotDone(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTodo")
    public ResponseEntity<Boolean> delete(@RequestParam(name = "id") int id) {
        return todoService.deleteTodo(id);
    }
    @PostMapping("/done")
    public ResponseEntity<TodoResponseDto> done(@RequestParam (name = "id") int id){
        return new ResponseEntity<>(todoService.done(id).getBody(),HttpStatus.OK);
    }

}

