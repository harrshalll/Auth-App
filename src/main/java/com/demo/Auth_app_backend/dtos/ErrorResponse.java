package com.demo.Auth_app_backend.dtos;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String message,
                            HttpStatus status) {

}
