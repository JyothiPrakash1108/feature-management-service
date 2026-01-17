package com.api.cms.security.provider;

import com.api.cms.entity.User;
import com.api.cms.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Optional;

public class ApiKeyAuthenticationProvider implements AuthenticationProvider {
    private UserRepository userRepository;
    public ApiKeyAuthenticationProvider(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       String apiKey = (String) authentication.getCredentials();
        Optional<User> optionalUser = userRepository.findByApiKey(apiKey);
        if (!optionalUser.isPresent()){
            throw new BadCredentialsException("Invalid Api key");
        }
        User user = optionalUser.get();
        return new ApiKeyAuthenticationToken(
                user,
                List.of(new SimpleGrantedAuthority("ROLE_CLIENT"))
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
