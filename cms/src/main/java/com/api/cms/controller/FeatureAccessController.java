package com.api.cms.controller;

import com.api.cms.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.cms.dto.FeatureCheckResponse;
import com.api.cms.dto.FeatureRequest;
import com.api.cms.service.FeatureAccessService;

@Controller
@RequestMapping("/api")
public class FeatureAccessController {
    private FeatureAccessService featureAccessService;
    @Autowired
    public FeatureAccessController(FeatureAccessService featureAccessService) {
        this.featureAccessService = featureAccessService;
    }
    @GetMapping("/check")
    public ResponseEntity<FeatureCheckResponse> isFeatureAccessibleToRole(@RequestParam String featureName,@RequestParam Role role) {
        boolean isAccessible = featureAccessService.isFeatureAccessibleToRole(featureName, role);
        FeatureCheckResponse response = new FeatureCheckResponse(isAccessible);
        return ResponseEntity.ok(response);
    }
    
}
