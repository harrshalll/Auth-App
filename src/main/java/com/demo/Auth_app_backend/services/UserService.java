package com.demo.Auth_app_backend.services;

import com.demo.Auth_app_backend.dtos.UserDto;

import java.util.Iterator;
import java.util.UUID;

public interface UserService {
    //create User
    UserDto createUser(UserDto userDto);

    //get User By email id
    UserDto getUserByEmail(String email);

    //Update User
    UserDto updateUser(UserDto userDto, UUID userId);

    //Delete USer
    void deleteUser(UUID userId);

    //get user by id
    UserDto getUserById(UUID userId);

    //get all users
    Iterable<UserDto> getAllUser();
}
