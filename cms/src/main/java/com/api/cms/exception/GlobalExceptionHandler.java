package com.api.cms.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleUserAlreadyExists(UserAlreadyExistsException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleCompanyAlreadyExists(CompanyAlreadyExistsException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(CompanyDoesNotExistException.class)
    public ResponseEntity<Map<String,String>> handleCompanyDoesNotExist(CompanyDoesNotExistException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String,String>> handleInvalidCredentials(InvalidCredentialsException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<Map<String,String>> handleUserDoesNotExist(UserDoesNotExistException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(NoSuchFeatureFlagExistsException.class)
    public ResponseEntity<Map<String,String>> handleNoSuchFeatureFlagExistsException(NoSuchFeatureFlagExistsException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleAdminNotFoundException(AdminNotFoundException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
