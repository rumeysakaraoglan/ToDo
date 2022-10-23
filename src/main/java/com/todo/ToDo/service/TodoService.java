package com.todo.ToDo.service;
import com.todo.ToDo.dataAccess.TodoListDao;
import com.todo.ToDo.dto.TodoAddRequestDto;
import com.todo.ToDo.dto.TodoResponseDto;
import com.todo.ToDo.dto.converter.TodoListDtoConverter;
import com.todo.ToDo.entity.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private final TodoListDao todoListDao;

    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public TodoService(TodoListDao todoListDao, SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.todoListDao = todoListDao;
    }

    public ResponseEntity<List<TodoResponseDto>> getAll() {
        List<TodoList> todoLists = todoListDao.findAll();
        List<TodoResponseDto> todoResponseDtos = new ArrayList<>();
        for (TodoList item : todoLists) {
            TodoResponseDto todoResponseDto = TodoListDtoConverter.convertTodoResponseDto(item);
            todoResponseDtos.add(todoResponseDto);
        }
        return new ResponseEntity<>(todoResponseDtos, HttpStatus.OK);
    }

    public ResponseEntity<TodoAddRequestDto> add(TodoList todoList) {
        todoList.setId(sequenceGeneratorService.getSequenceNumber(todoList.SEQUENCE_NAME));
        todoListDao.save(todoList);
        TodoAddRequestDto todoAddRequestDto = TodoListDtoConverter.convertTodoAddRequestDto(todoList);
        return new ResponseEntity<TodoAddRequestDto>(todoAddRequestDto, HttpStatus.OK);
    }

    public List<TodoResponseDto> getWeek() {
        List<TodoResponseDto> todoLists = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        int weekOfYear = localDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        getAll().getBody().forEach((item) -> {
            if (item.getStartTime().get(ChronoField.ALIGNED_WEEK_OF_YEAR) == weekOfYear) {
                todoLists.add(item);
            }
        });
        return todoLists;
    }

    public List<TodoResponseDto> getMonth() {
        List<TodoResponseDto> todoLists = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        int monthOfYear = localDate.get(ChronoField.MONTH_OF_YEAR);
        getAll().getBody().forEach((item) -> {
            if (item.getStartTime().get(ChronoField.MONTH_OF_YEAR) == monthOfYear) {
                todoLists.add(item);
            }
        });
        return todoLists;
    }

    public List<TodoResponseDto> getDay() {
        List<TodoResponseDto> todoLists = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        int dayOfYear = localDate.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR);
        getAll().getBody().forEach((item) -> {
            if (item.getStartTime().get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR) == dayOfYear) {
                todoLists.add(item);
            }
        });
        return todoLists;
    }

    public List<TodoResponseDto> getNotDone() {
        List<TodoResponseDto> todoLists = new ArrayList<>();
        getAll().getBody().forEach((item) -> {
            if (!item.isDone()) {
                todoLists.add(item);
            }
        });
        return todoLists;
    }

    public ResponseEntity<Boolean> deleteTodo(int id) {
        todoListDao.deleteById(id);
        return new ResponseEntity<>(!todoListDao.existsById(id),HttpStatus.OK);
    }

    public ResponseEntity<TodoResponseDto> done(int id) {
        List<TodoList> todoLists = todoListDao.findAll();
        TodoResponseDto todoResponseDto = null;
        for (TodoList item : todoLists) {
            if (item.getId() == id) {
                item.setDone(!item.isDone());
                todoResponseDto = TodoListDtoConverter.convertTodoResponseDto(item);
                todoListDao.save(item);
            }
        }
        return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
    }
}
