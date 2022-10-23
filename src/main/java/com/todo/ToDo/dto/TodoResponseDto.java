package com.todo.ToDo.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDto {
    private int author;
    private String description;
    private LocalDate startTime;
    private LocalDate endTime;
    private boolean done;
}