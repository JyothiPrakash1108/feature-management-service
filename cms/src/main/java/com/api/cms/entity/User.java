package com.api.cms.entity;


import com.api.cms.enums.Role;

import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Entity
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "company_id"})
    }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "company_id", nullable = false)
    private UUID companyId;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private boolean active = true;

    public User() {
    }
    public User(String username, String email, String password, UUID companyId, Role role, boolean active) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.companyId = companyId;
        this.role = role;
        this.active = active;
    }
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
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
    public UUID getCompanyId() {
        return companyId;
    }
    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    
}
