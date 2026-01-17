package com.api.cms.service;

import com.api.cms.exception.UserAlreadyExists;
import com.api.cms.mapper.UserMapper;
import com.api.cms.util.ApiUtil;
import org.springframework.stereotype.Service;

import com.api.cms.dto.UserRequest;
import com.api.cms.entity.User;
import com.api.cms.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(UserRequest userRequest) throws UserAlreadyExists {
        Optional<User> existingUser = userRepository.findByEmail(userRequest.getEmail());
        if(existingUser.isPresent()){
            throw new UserAlreadyExists("user alredy exists with email : "+userRequest.getEmail() +" and username : "+userRequest.getUsername() );
        }
        User user = UserMapper.toUser(userRequest);
        user.setApiKey(ApiUtil.generateApiKey());
        return userRepository.save(user);
    }
}
