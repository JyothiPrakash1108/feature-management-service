package com.api.cms.dto.company;

import com.api.cms.dto.AdminInfoDTO;
import jakarta.validation.constraints.NotBlank;

public class CreateCompanyRequestDTO {
    @NotBlank(message = "companyname required")
    private String companyName;
    private AdminInfoDTO admin;

    
    public CreateCompanyRequestDTO() {
    }


    public CreateCompanyRequestDTO(String companyName, AdminInfoDTO admin) {
        this.companyName = companyName;
        this.admin = admin;
    }


    public String getCompanyName() {
        return companyName;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public AdminInfoDTO getAdmin() {
        return admin;
    }


    public void setAdmin(AdminInfoDTO admin) {
        this.admin = admin;
    }
    
}
