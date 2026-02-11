package com.api.cms.security.apikeyauth;

import java.util.UUID;

public class ApiKeyPrincipal {
    private UUID companyId;
    public ApiKeyPrincipal(UUID companyId) {
        this.companyId = companyId;
    }
    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }
    public UUID getCompanyId() {
        return companyId;
    }
}
