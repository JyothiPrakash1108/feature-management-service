package com.api.cms.dto.featureflag;

public class CreateFeatureFlagRequestDTO {
    private String featureFlagName;
    private boolean enabled;
    public CreateFeatureFlagRequestDTO() {
    }
    public CreateFeatureFlagRequestDTO(String featureFlagName, boolean enabled) {
        this.featureFlagName = featureFlagName;
        this.enabled = enabled;
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
