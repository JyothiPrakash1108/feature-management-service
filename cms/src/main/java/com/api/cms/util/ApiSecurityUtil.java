package com.api.cms.util;

import java.util.UUID;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.api.cms.security.apikeyauth.ApiKeyPrincipal;

public class ApiSecurityUtil {
private ApiSecurityUtil() {}

    private static Authentication getAuthentication() {
        Authentication auth =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new AccessDeniedException("Unauthenticated API request");
        }
        return auth;
    }

    public static UUID getCompanyId() {
        Object principal = getAuthentication().getPrincipal();

        if (principal instanceof ApiKeyPrincipal apiKeyPrincipal) {
            return apiKeyPrincipal.getCompanyId();
        }

        throw new AccessDeniedException(
                "API key authentication required"
        );
    }
}
