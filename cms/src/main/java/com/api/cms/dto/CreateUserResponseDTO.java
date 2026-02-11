package com.api.cms.dto;

import com.api.cms.enums.Role;

public class CreateUserResponseDTO {
    private String username;
    private String email;
    private Role role;
    public CreateUserResponseDTO() {
    }
    public CreateUserResponseDTO(String username, String email, Role role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    
}
