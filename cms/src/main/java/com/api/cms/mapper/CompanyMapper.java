package com.api.cms.mapper;


import com.api.cms.dto.AdminInfoResponseDTO;
import com.api.cms.dto.company.CreateCompanyResponseDTO;
import com.api.cms.entity.Company;
import com.api.cms.entity.User;

public class CompanyMapper {
    public static CreateCompanyResponseDTO toCreateCompanyResponseDTO(Company company,User admin) {
       CreateCompanyResponseDTO responseDTO = new CreateCompanyResponseDTO();
       responseDTO.setCompanyId(company.getCompanyId());
        responseDTO.setCompanyName(company.getCompanyName());
        responseDTO.setApiKey(company.getApiKey());
        responseDTO.setCreatedAt(company.getCreatedAt());
        AdminInfoResponseDTO adminInfo = new AdminInfoResponseDTO(admin.getUsername(), admin.getEmail());
        responseDTO.setAdminInfo(adminInfo);
        return responseDTO;
    }
}
