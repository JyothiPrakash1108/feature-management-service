package com.api.cms.dto;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;

public class FeatureFlagDTO {
     private UUID featureFlagId;
    private UUID companyId;
    private String name;
    private boolean enabled;
    private Instant createdAt;
    private Instant updatedAt;
    public FeatureFlagDTO() {
    }
    public FeatureFlagDTO(UUID featureFlagId, UUID companyId, String name, boolean enabled, Instant createdAt,
            Instant updatedAt) {
        this.featureFlagId = featureFlagId;
        this.companyId = companyId;
        this.name = name;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public UUID getFeatureFlagId() {
        return featureFlagId;
    }
    public void setFeatureFlagId(UUID featureFlagId) {
        this.featureFlagId = featureFlagId;
    }
    public UUID getCompanyId() {
        return companyId;
    }
    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public Instant getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
