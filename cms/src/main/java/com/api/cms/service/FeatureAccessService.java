package com.api.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cms.repository.FeatureAccessRepository;
import com.api.cms.repository.FeatureRepository;

@Service
public class FeatureAccessService {
    private FeatureAccessRepository featureAccessRepository;
    private FeatureRepository featureRepository;
    @Autowired
    public FeatureAccessService(FeatureAccessRepository featureAccessRepository,FeatureRepository featureRepository) {
        this.featureAccessRepository = featureAccessRepository;
        this.featureRepository = featureRepository;
    }
    public boolean isFeatureAccessibleToRole(String featureName, Long roleId) {
        return featureAccessRepository.existsByFeatureNameAndRoleId(featureName, roleId);
    }
}
