package com.api.cms.mapper;

import com.api.cms.dto.FeatureFlagDTO;
import com.api.cms.dto.FeatureToggleResponseDTO;
import com.api.cms.dto.featureflag.CreateFeatureFlagResponseDTO;
import com.api.cms.entity.FeatureFlag;

public class FeatureFlagMapper {
    public static CreateFeatureFlagResponseDTO toCreateFeatureFlagResponse(FeatureFlag featureFlag) {
        CreateFeatureFlagResponseDTO responseDTO = new CreateFeatureFlagResponseDTO();
        responseDTO.setFeatureFlagId(featureFlag.getFeatureFlagId());
        responseDTO.setFeatureFlagName(featureFlag.getName());
        responseDTO.setEnabled(featureFlag.isEnabled());
        return responseDTO;
    }
    public static FeatureToggleResponseDTO toFeatureToggleResponse(FeatureFlag featureFlag) {
        FeatureToggleResponseDTO responseDTO = new FeatureToggleResponseDTO();
        responseDTO.setFeatureFlagId(featureFlag.getFeatureFlagId());
        responseDTO.setFeatureFlagName(featureFlag.getName());
        responseDTO.setEnabled(featureFlag.isEnabled());
        return responseDTO;
    }
    public static FeatureFlagDTO toFeatureFlagDTO(FeatureFlag featureFlag) {
        FeatureFlagDTO dto = new FeatureFlagDTO();
        dto.setFeatureFlagId(featureFlag.getFeatureFlagId());
        dto.setCompanyId(featureFlag.getCompanyId());
        dto.setName(featureFlag.getName());
        dto.setEnabled(featureFlag.isEnabled());
        dto.setCreatedAt(featureFlag.getCreatedAt());
        dto.setUpdatedAt(featureFlag.getUpdatedAt());
        return dto;
    }
}