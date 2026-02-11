package com.api.cms.dto.company;

import java.time.Instant;
import java.util.UUID;

import com.api.cms.dto.AdminInfoResponseDTO;

public class CreateCompanyResponseDTO {
    private UUID companyId;
    private String companyName;
    private Instant createdAt;
    private String apiKey;
    private AdminInfoResponseDTO adminInfo;
    public CreateCompanyResponseDTO() {
    }
    public CreateCompanyResponseDTO(UUID companyId, String companyName, Instant createdAt, String apiKey, AdminInfoResponseDTO adminInfo) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.createdAt = createdAt;
        this.apiKey = apiKey;
        this.adminInfo = adminInfo;
    }

    public UUID getCompanyId() {
        return companyId;
    }
    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    public AdminInfoResponseDTO getAdminInfo() {
        return adminInfo;
    }
    public void setAdminInfo(AdminInfoResponseDTO adminInfo) {
        this.adminInfo = adminInfo;
    }
}
