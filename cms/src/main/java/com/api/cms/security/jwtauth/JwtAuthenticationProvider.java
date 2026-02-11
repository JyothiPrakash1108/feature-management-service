package com.api.cms.security.jwtauth;

import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.api.cms.repository.UserRepo;
import com.api.cms.util.JWTUtil;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String token = (String) authentication.getCredentials();

        Claims claims = JWTUtil.extractAllClaims(token);

        String userId = claims.get("userId", String.class);
        String role = claims.get("role", String.class);
         JWTUserPrincipal principal = new JWTUserPrincipal(
            UUID.fromString(claims.getSubject()),          // userId
            UUID.fromString(claims.get("companyId", String.class)),
            claims.get("role", String.class)
    );
        return new JWTAuthenticationToken(
            principal,
            List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
    );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

