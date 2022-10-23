package com.todo.ToDo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "todolist")
public class TodoList {

    @Transient
    public static final String SEQUENCE_NAME = "todolist_sequence";

    private int userId;
    @Id
    private int id;
    private String description;
    private LocalDate startTime;
    private LocalDate endTime;
    private boolean done;
}
