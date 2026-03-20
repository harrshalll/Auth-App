package com.demo.Auth_app_backend.security;

import com.demo.Auth_app_backend.entities.User;
import com.demo.Auth_app_backend.exceptions.ResourceNotFoundException;
import com.demo.Auth_app_backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Use this when you want to log through more than one type like email, username, password
       // User user;
//        if (input.contains("@")) {
//            user = userRepository.findByEmail(input).orElseThrow(()-> new ResourceNotFoundException("Invalid Email id or Password!!"));
//        } else if (input.matches("\\d{10}")) {
//            user =  userRepository.findByPhoneNo(input).orElseThrow(()-> new ResourceNotFoundException("Invalid Phone No or password!!"));
//        }
        return userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("Invalid Email or Password!!"));
    }
}
