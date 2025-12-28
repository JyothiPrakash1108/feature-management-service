package com.api.cms.service;

import org.springframework.stereotype.Service;

import com.api.cms.dto.UserRequest;
import com.api.cms.entity.Users;
import com.api.cms.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Users createUser(UserRequest userRequest) {
        Users user = new Users();
        return userRepository.save(user);
    }
}
