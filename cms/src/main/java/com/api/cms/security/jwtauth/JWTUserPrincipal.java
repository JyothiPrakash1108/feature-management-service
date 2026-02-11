package com.api.cms.security.jwtauth;

import java.util.UUID;

public class JWTUserPrincipal {
    private UUID userId;
    private UUID companyId;
    private String role;
    public JWTUserPrincipal(){

    }
    public JWTUserPrincipal(UUID userId, UUID companyId, String role) {
        this.userId = userId;
        this.companyId = companyId;
        this.role = role;
    }
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public UUID getCompanyId() {
        return companyId;
    }
    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
