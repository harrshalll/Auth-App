package com.demo.Auth_app_backend.dtos;

public record LoginRequest(
        String email,
        String password
) {
}
