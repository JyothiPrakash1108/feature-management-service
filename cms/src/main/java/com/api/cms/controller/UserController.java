package com.api.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.api.cms.dto.UserRequest;
import com.api.cms.entity.Users;
import com.api.cms.service.UserService;

@Controller
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        Users user = userService.createUser(userRequest);
        return ResponseEntity.ok(new UserResponse());
    }
}
