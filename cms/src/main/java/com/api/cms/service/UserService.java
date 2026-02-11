package com.api.cms.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.cms.dto.CreateUserRequestDTO;
import com.api.cms.dto.UpdateUserRequestDTO;
import com.api.cms.entity.User;
import com.api.cms.enums.Role;
import com.api.cms.exception.AdminNotFoundException;
import com.api.cms.exception.CompanyAlreadyExistsException;
import com.api.cms.exception.UserAlreadyExistsException;
import com.api.cms.exception.UserDoesNotExistException;
import com.api.cms.repository.UserRepo;
import com.api.cms.util.SecurityUtil;

@Service
public class UserService {
    private UserRepo userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserRequestDTO requestDTO) throws UserAlreadyExistsException {
        String email = requestDTO.getEmail();
        UUID companyId = SecurityUtil.getCompanyId();
        if (userRepository.existsByEmailAndCompanyId(email, companyId)) {
            throw new UserAlreadyExistsException("User with email " + email + " already exists.");
        }
        User newUser = new User();
        newUser.setActive(true);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        newUser.setRole(Role.MEMBER);
        newUser.setUsername(requestDTO.getUsername());
        newUser.setCompanyId(companyId);
        return userRepository.save(newUser);
    }

    public User getAdminByCompanyId(UUID companyId) throws AdminNotFoundException {
        Optional<User> adminUser = userRepository.findByCompanyIdAndRole(companyId, Role.ADMIN);
        if (adminUser.isPresent()) {
            return adminUser.get();
        }
        throw new AdminNotFoundException("Admin user not found for company ID: " + companyId);
    }

    public User updateUser(UUID userId, UpdateUserRequestDTO requestDTO) throws UserDoesNotExistException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserDoesNotExistException("User with id : "+userId+" not found"));
        String email = requestDTO.getEmail();
        if(email != null){
            user.setEmail(email);
        }
        String username = requestDTO.getUsername();
        if(username != null){
            user.setUsername(username);
        }
        
        String role = requestDTO.getRole() != null ? requestDTO.getRole().name() : null;
        if(role != null){
            user.setRole(requestDTO.getRole());
        }
        return userRepository.save(user);
    }
}
