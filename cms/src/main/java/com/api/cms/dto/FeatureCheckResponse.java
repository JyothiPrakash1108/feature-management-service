package com.api.cms.dto;

public class FeatureCheckResponse {
    private boolean enabled;
    public FeatureCheckResponse(boolean enabled) {
        this.enabled = enabled;
    }
    public boolean isEnabled() {
        return enabled;
    }    
}
