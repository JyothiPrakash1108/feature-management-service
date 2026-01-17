package com.api.cms.mapper;

import com.api.cms.dto.CreateUserResponse;
import com.api.cms.dto.UserRequest;
import com.api.cms.dto.UserResponse;
import com.api.cms.entity.User;

public class UserMapper {
    public static User toUser(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        return user;
    }

    public static CreateUserResponse toCreateUserResponse(User user) {
        CreateUserResponse response = new CreateUserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setApiKey(user.getApiKey());
        return response;
    }

    public static UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        return response;
    }

}
