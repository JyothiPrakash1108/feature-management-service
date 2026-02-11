package com.api.cms.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.cms.dto.CreateUserRequestDTO;
import com.api.cms.dto.CreateUserResponseDTO;
import com.api.cms.dto.UpdateUserRequestDTO;
import com.api.cms.dto.UpdateUserResponseDTO;
import com.api.cms.entity.User;
import com.api.cms.exception.UserAlreadyExistsException;
import com.api.cms.exception.UserDoesNotExistException;
import com.api.cms.mapper.UserMapper;
import com.api.cms.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateUserResponseDTO> createUser(@Valid @RequestBody CreateUserRequestDTO requestDTO) throws UserAlreadyExistsException {
        User savedUser = userService.createUser(requestDTO);
        CreateUserResponseDTO responseDTO = UserMapper.toCreateUserResponseDTO(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @PatchMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateUserResponseDTO> updateUser(@PathVariable UUID userId, @Valid @RequestBody UpdateUserRequestDTO requestDTO) throws UserDoesNotExistException{
        User updatedUser = userService.updateUser(userId, requestDTO);
        UpdateUserResponseDTO responseDTO = UserMapper.toUpdateUserResponseDTO(updatedUser);
        return ResponseEntity.ok(responseDTO);
    }
}
