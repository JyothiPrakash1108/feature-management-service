package com.api.cms.dto;

public class FeatureToggleRequestDTO {
    private boolean enabled;
    public FeatureToggleRequestDTO(boolean enabled) {
        this.enabled = enabled;
    }   

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
