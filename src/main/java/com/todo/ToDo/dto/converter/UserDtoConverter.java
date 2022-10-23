package com.todo.ToDo.dto.converter;


import com.todo.ToDo.dto.UserRequestDto;
import com.todo.ToDo.dto.UserResponseDto;
import com.todo.ToDo.entity.User;

public class UserDtoConverter {
    public static UserResponseDto convertUserResponseDto(User user) {
        return new UserResponseDto(
                user.getName()
        );
    }
    public static UserRequestDto convertUserRequestDto(User user) {
        return new UserRequestDto(
                user.getName()
        );
    }
}
