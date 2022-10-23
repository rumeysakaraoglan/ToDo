package com.todo.ToDo.dataAccess;


import com.todo.ToDo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User, Integer> {
}