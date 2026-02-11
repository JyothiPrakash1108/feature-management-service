package com.api.cms.dto.featureflag;

import java.util.UUID;

public class CreateFeatureFlagResponseDTO {
    private UUID featureFlagId;
    private String featureFlagName;
    private boolean enabled;
    public CreateFeatureFlagResponseDTO() {
    }
    public CreateFeatureFlagResponseDTO(UUID featureFlagId, String featureFlagName, boolean enabled) {
        this.featureFlagId = featureFlagId;
        this.featureFlagName = featureFlagName;
        this.enabled = enabled;
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
