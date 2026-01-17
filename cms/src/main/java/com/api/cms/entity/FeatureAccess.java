package com.api.cms.entity;

import com.api.cms.enums.Role;

import jakarta.persistence.*;

@Entity
public class FeatureAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne
    private Feature feature;
    public FeatureAccess(){

    }
    public FeatureAccess(Feature feature, Long id, Role role) {
        this.feature = feature;
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

}
