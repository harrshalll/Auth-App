package com.demo.Auth_app_backend.services.serviceImpl;

import com.demo.Auth_app_backend.dtos.UserDto;
import com.demo.Auth_app_backend.entities.Provider;
import com.demo.Auth_app_backend.entities.User;
import com.demo.Auth_app_backend.exceptions.ResourceNotFoundException;
import com.demo.Auth_app_backend.repositories.UserRepository;
import com.demo.Auth_app_backend.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new IllegalArgumentException("User With this Email already Exists!");
        }
        User user = modelMapper.map(userDto, User.class);
        if (userDto.getProvider() != null) {
            userDto.setProvider(userDto.getProvider());
        }else{
            userDto.setProvider(Provider.LOCAL);
        }
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    @Transactional
    public UserDto getUserByEmail(String email) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User Not Found With email id"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto, UUID userId) {
        //In our application user cannot change its email
        //TODO:change password updation logic...
        User existingUser = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException(("User with given id does not exists")));
        if (userDto.getName() != null) {
            existingUser.setName(userDto.getName());
        }
        if (userDto.getPassword() != null) {
            existingUser.setPassword(userDto.getPassword());
        }
        if (userDto.getImage() != null) {
            existingUser.setImage(userDto.getImage());
        }
        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id does not exists"));
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserById(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id does not exists"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @Transactional
    public Iterable<UserDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(user  -> modelMapper.map(user, UserDto.class))
                .toList();
    }
}
