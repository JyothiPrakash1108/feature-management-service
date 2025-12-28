package com.api.cms.service;

import org.springframework.stereotype.Service;

import com.api.cms.dto.FeatureRequest;
import com.api.cms.entity.Feature;
import com.api.cms.repository.FeatureRepository;

@Service
public class FeatureService {
    private FeatureRepository featureRepository;
    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }
    public Feature createFeature(FeatureRequest featureRequest) {
        Feature feature = new Feature();
        feature.setName(featureRequest.getName());
        feature.setDescription(featureRequest.getDescription());
        feature.setEnabled(featureRequest.isEnabled());
        return featureRepository.save(feature);
    }
}
