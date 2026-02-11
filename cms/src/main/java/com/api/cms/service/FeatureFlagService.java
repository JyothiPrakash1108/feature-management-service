package com.api.cms.service;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.api.cms.dto.featureflag.CreateFeatureFlagRequestDTO;
import com.api.cms.entity.FeatureFlag;
import com.api.cms.enums.Role;
import com.api.cms.exception.FeatureFlagAlreadyExistsException;
import com.api.cms.exception.NoSuchFeatureFlagExistsException;
import com.api.cms.repository.FeatureFlagRepo;
import com.api.cms.util.SecurityUtil;

@Service
public class FeatureFlagService {
    private FeatureFlagRepo featureFlagRepo;
    public FeatureFlagService(FeatureFlagRepo featureFlagRepo) {
        this.featureFlagRepo = featureFlagRepo;
    }
    public FeatureFlag createFeatureFlag(CreateFeatureFlagRequestDTO createFeatureFlagDTO) throws AccessDeniedException, FeatureFlagAlreadyExistsException {
        UUID companyId = SecurityUtil.getCompanyId();
        String featureName = createFeatureFlagDTO.getFeatureFlagName();
        String role = SecurityUtil.getRole();
        if(!"ADMIN".equals(role)){
            throw new AccessDeniedException("only admin of this company can create feature flags");
        }
        if(featureFlagRepo.findByCompanyIdAndName(companyId, featureName).isPresent()){
            throw new FeatureFlagAlreadyExistsException("feature with name "+featureName+" already exists in your company");
        }
        FeatureFlag featureFlag = new FeatureFlag();
        featureFlag.setCompanyId(companyId);
        featureFlag.setName(featureName);
        featureFlag.setEnabled(createFeatureFlagDTO.isEnabled());
        featureFlag.setCreatedAt(Instant.now());
        featureFlag.setUpdatedAt(Instant.now());
        return featureFlagRepo.save(featureFlag);
    }
    public boolean isFeatureEnabledForCompany(String featureName, UUID companyId) {
        return featureFlagRepo.findByCompanyIdAndName(companyId,featureName)
                .map(FeatureFlag::isEnabled)
                .orElse(false);
    }
    public FeatureFlag toggleFeatureFlagStatus(String featureName,UUID companyId,boolean enabled) throws NoSuchFeatureFlagExistsException {
        FeatureFlag featureFlag = featureFlagRepo.findByCompanyIdAndName(companyId,featureName)
                .orElseThrow( () -> new NoSuchFeatureFlagExistsException("No feature flag with name "+featureName+" exists in your company"));
        featureFlag.setEnabled(enabled);
        featureFlag.setUpdatedAt(Instant.now());
        return featureFlagRepo.save(featureFlag);
    }
    public List<FeatureFlag> getAllFeatureFlags(UUID companyId) {
        List<FeatureFlag> featureFlags = featureFlagRepo.findAllByCompanyId(companyId); 
        if(featureFlags.isEmpty()){
            return Collections.emptyList();
        }
        return featureFlags.stream().toList();
    }
    public FeatureFlag getFeatureFlagByName(String featureName, UUID companyId) throws NoSuchFeatureFlagExistsException {
        return featureFlagRepo.findByCompanyIdAndName(companyId,featureName)
                .orElseThrow( () -> new NoSuchFeatureFlagExistsException("No feature flag with name "+featureName+" exists in your company"));
    }
    public FeatureFlag deleteFeatureFlag(UUID companyId, String featureName) throws NoSuchFeatureFlagExistsException {
        FeatureFlag featureFlag = featureFlagRepo.findByCompanyIdAndName(companyId,featureName)
                .orElseThrow( () -> new NoSuchFeatureFlagExistsException("No feature flag with name "+featureName+" exists in your company"));
        featureFlagRepo.delete(featureFlag);
        return featureFlag;
    }
}
