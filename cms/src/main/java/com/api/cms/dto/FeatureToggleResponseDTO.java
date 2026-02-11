package com.api.cms.dto;

import java.util.UUID;

public class FeatureToggleResponseDTO {
    private UUID featureFlagId;
    private String featureFlagName;
    private boolean enabled;
    public FeatureToggleResponseDTO() {
    }
    public UUID getFeatureFlagId() {
        return featureFlagId;
    }
    public void setFeatureFlagId(UUID featureFlagId) {
        this.featureFlagId = featureFlagId;
    }
    public String getFeatureFlagName() {
        return featureFlagName;
    }
    public void setFeatureFlagName(String featureFlagName) {
        this.featureFlagName = featureFlagName;
    }
    public boolean isEnabled() {
        return enabled;
    }  
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
