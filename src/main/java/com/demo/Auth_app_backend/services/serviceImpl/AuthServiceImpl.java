package com.demo.Auth_app_backend.services.serviceImpl;

import com.demo.Auth_app_backend.config.SecurityConfig;
import com.demo.Auth_app_backend.dtos.UserDto;
import com.demo.Auth_app_backend.repositories.UserRepository;
import com.demo.Auth_app_backend.services.AuthService;
import com.demo.Auth_app_backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;
    @Override
    public UserDto register(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));//Encoding the password
        return userService.createUser(userDto);// creates the User
    }
}
