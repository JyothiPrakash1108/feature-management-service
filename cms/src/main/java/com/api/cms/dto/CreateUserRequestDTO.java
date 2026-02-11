package com.api.cms.dto;

import java.util.UUID;

import com.api.cms.enums.Role;
import jakarta.validation.constraints.NotBlank;

public class CreateUserRequestDTO {
    @NotBlank(message = "username required")
    private String username;
    @NotBlank(message = "email required")
    private String email;
    @NotBlank(message = "password required")
    private String password;
    @NotBlank(message = "role missing")
    private Role role;
    public CreateUserRequestDTO() {
    }
    public CreateUserRequestDTO(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
