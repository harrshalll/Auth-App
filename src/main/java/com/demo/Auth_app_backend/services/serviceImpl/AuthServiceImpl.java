package com.demo.Auth_app_backend.services.serviceImpl;

import com.demo.Auth_app_backend.dtos.UserDto;
import com.demo.Auth_app_backend.repositories.UserRepository;
import com.demo.Auth_app_backend.services.AuthService;
import com.demo.Auth_app_backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    @Override
    public UserDto register(UserDto userDto) {
        UserDto userDto1 = userService.createUser(userDto);
        return userDto1;
    }
}
