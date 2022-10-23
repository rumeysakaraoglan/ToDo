package com.todo.ToDo.controller;

import com.todo.ToDo.dto.UserRequestDto;
import com.todo.ToDo.dto.UserResponseDto;
import com.todo.ToDo.entity.User;
import com.todo.ToDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return new ResponseEntity<List<UserResponseDto>>(userService.getAll().getBody(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserRequestDto> add(@RequestBody User user) {
        return new ResponseEntity<UserRequestDto>(userService.add(user).getBody(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Boolean> delete(@RequestParam(name = "id") int id) {
        return userService.deleteUser(id);
    }
}

