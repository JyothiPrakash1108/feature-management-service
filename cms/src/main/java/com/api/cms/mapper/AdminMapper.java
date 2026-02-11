package com.api.cms.mapper;

import com.api.cms.dto.AdminInfoDTO;
import com.api.cms.entity.User;

public class AdminMapper {
    public static AdminInfoDTO toAdminInfoDTO(User admin) {
        return new AdminInfoDTO(admin.getUsername(), admin.getEmail(), admin.getPassword());
    }
}
