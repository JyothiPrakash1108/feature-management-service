package com.api.cms.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.cms.dto.auth.LoginRequestDTO;
import com.api.cms.entity.User;
import com.api.cms.exception.UserDoesNotExistException;
import com.api.cms.repository.UserRepo;
import com.api.cms.util.JWTUtil;

@Service
public class AuthService implements UserDetailsService{
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

     @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepo.findByEmail(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User with email " + username + " does not exist");
        }
        return org.springframework.security.core.userdetails.User.withUsername(username)
                .password(userOpt.get().getPassword())
                .authorities(userOpt.get().getRole().name())
                .build();
    }
    public String generateJwtToken(String email) throws UserDoesNotExistException {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new UserDoesNotExistException("User with email " + email + " does not exist");
        }
        return JWTUtil.generateToken(userOpt.get());
    }
}
