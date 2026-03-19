package com.demo.Auth_app_backend.services;

import com.demo.Auth_app_backend.dtos.UserDto;

public interface AuthService {
    UserDto register(UserDto userDto);
}
