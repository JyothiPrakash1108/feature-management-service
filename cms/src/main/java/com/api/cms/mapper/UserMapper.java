package com.api.cms.mapper;

import org.hibernate.sql.Update;

import com.api.cms.dto.CreateUserResponseDTO;
import com.api.cms.dto.UpdateUserResponseDTO;
import com.api.cms.entity.User;

public class UserMapper {
    public static CreateUserResponseDTO toCreateUserResponseDTO(User user) {
        return new CreateUserResponseDTO(user.getUsername(), user.getEmail(), user.getRole());
    }
    public static UpdateUserResponseDTO toUpdateUserResponseDTO(User user) {
        return new UpdateUserResponseDTO(user.getUsername(), user.getEmail(), user.getRole());
    }
}
