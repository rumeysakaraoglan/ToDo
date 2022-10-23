package com.todo.ToDo.dataAccess;


import com.todo.ToDo.entity.TodoList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoListDao extends MongoRepository<TodoList, Integer> {
}
