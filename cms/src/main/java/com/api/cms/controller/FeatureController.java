package com.api.cms.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.cms.dto.FeatureRequest;
import com.api.cms.entity.Feature;
import com.api.cms.service.FeatureService;

@Controller
public class FeatureController {
    private FeatureService featureService;
    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }
    @PostMapping("/api/features")
    public ResponseEntity createFeature(@RequestBody FeatureRequest featureRequest) {
        Feature feature = featureService.createFeature(featureRequest);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(feature);
    }
}
