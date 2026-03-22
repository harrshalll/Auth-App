package com.demo.Auth_app_backend.exceptions;

import com.demo.Auth_app_backend.dtos.ApiError;
import com.demo.Auth_app_backend.dtos.ErrorResponse;
import com.demo.Auth_app_backend.services.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //resource not found exception handle : method
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ErrorResponse internalServerError = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(internalServerError);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        ErrorResponse internalServerError = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(internalServerError);
    }

    @ExceptionHandler({
            UsernameNotFoundException.class,
            BadCredentialsException.class,
            CredentialsExpiredException.class,
            DisabledException.class
    })
    public ResponseEntity<ApiError> handleAuthException(Exception exception, HttpServletRequest request) {
       var apiError = ApiError.of(HttpStatus.BAD_REQUEST.value(),"Bad Request", exception.getMessage(), request.getRequestURI());
       return ResponseEntity.badRequest().body(apiError);
    }
}
