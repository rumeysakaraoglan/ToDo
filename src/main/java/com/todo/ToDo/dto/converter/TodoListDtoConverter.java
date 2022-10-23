package com.todo.ToDo.dto.converter;


import com.todo.ToDo.dto.TodoAddRequestDto;
import com.todo.ToDo.dto.TodoResponseDto;
import com.todo.ToDo.entity.TodoList;

public class TodoListDtoConverter {
    public static TodoAddRequestDto convertTodoAddRequestDto(TodoList from) {
        return new TodoAddRequestDto(
                from.getUserId(),
                from.getDescription(),
                from.getStartTime(),
                from.getEndTime(),
                from.isDone()
        );
    }
    public static TodoResponseDto convertTodoResponseDto(TodoList from) {
        return new TodoResponseDto(
                from.getUserId(),
                from.getDescription(),
                from.getStartTime(),
                from.getEndTime(),
                from.isDone()
        );
    }
}