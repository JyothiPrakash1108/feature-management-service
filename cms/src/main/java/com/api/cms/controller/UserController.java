package com.api.cms.controller;

import com.api.cms.dto.CreateUserResponse;
import com.api.cms.dto.UserResponse;
import com.api.cms.exception.UserAlreadyExists;
import com.api.cms.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.api.cms.dto.UserRequest;
import com.api.cms.entity.User;
import com.api.cms.service.UserService;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody UserRequest userRequest) throws UserAlreadyExists {
        User user = userService.createUser(userRequest);
        CreateUserResponse savedUser = UserMapper.toCreateUserResponse(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/users/{id}")
    public String  updateUSer(@PathVariable Long id,@RequestBody UserRequest userRequest){
        return "hey this end point is working";
    }
}
