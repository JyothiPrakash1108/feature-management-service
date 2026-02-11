package com.api.cms.util;

import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;

import com.api.cms.enums.Role;
import com.api.cms.security.jwtauth.JWTUserPrincipal;

public class SecurityUtil {
    public static JWTUserPrincipal getPrincipal(){
        if (SecurityContextHolder.getContext() == null ||
            SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof JWTUserPrincipal) {
            return (JWTUserPrincipal) principal;
        }
        return null;
    }
    public static UUID getCompanyId(){
        JWTUserPrincipal principal = getPrincipal();
        if (principal == null) {
            throw new IllegalStateException("No authenticated principal available (check authentication/security filter order or secure the endpoint)");
        }
        return principal.getCompanyId();
    }
    public static String getRole(){
        JWTUserPrincipal principal = getPrincipal();
        if (principal == null) {
            throw new IllegalStateException("No authenticated principal available (check authentication/security filter order or secure the endpoint)");
        }
        return principal.getRole();
    }
}
