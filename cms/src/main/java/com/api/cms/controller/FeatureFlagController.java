package com.api.cms.controller;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.cms.dto.FeatureFlagDTO;
import com.api.cms.dto.FeatureToggleRequestDTO;
import com.api.cms.dto.FeatureToggleResponseDTO;
import com.api.cms.dto.featureflag.CreateFeatureFlagRequestDTO;
import com.api.cms.dto.featureflag.CreateFeatureFlagResponseDTO;
import com.api.cms.entity.FeatureFlag;
import com.api.cms.exception.FeatureFlagAlreadyExistsException;
import com.api.cms.exception.NoSuchFeatureFlagExistsException;
import com.api.cms.mapper.FeatureFlagMapper;
import com.api.cms.service.FeatureFlagService;
import com.api.cms.util.SecurityUtil;

import jakarta.validation.Valid;

@RestController
public class FeatureFlagController {
    private FeatureFlagService featureFlagService;
    public FeatureFlagController(FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }
    @PostMapping("/feature-flags")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateFeatureFlagResponseDTO> createFeatureFlag(@Valid @RequestBody CreateFeatureFlagRequestDTO createFeatureFlagDTO) throws AccessDeniedException, FeatureFlagAlreadyExistsException {
        FeatureFlag featureFlag = featureFlagService.createFeatureFlag(createFeatureFlagDTO);
        CreateFeatureFlagResponseDTO responseDTO = FeatureFlagMapper.toCreateFeatureFlagResponse(featureFlag);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @PatchMapping("/feature-flags/{featureName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FeatureToggleResponseDTO> toggleFeatureFlagStatus(@PathVariable String featureName , @RequestBody FeatureToggleRequestDTO toggleRequest) throws NoSuchFeatureFlagExistsException {
        UUID companyId = SecurityUtil.getCompanyId();
        FeatureFlag featureFlag =  featureFlagService.toggleFeatureFlagStatus(featureName, companyId, toggleRequest.isEnabled());
        FeatureToggleResponseDTO responseDTO = FeatureFlagMapper.toFeatureToggleResponse(featureFlag);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("/feature-flags")
    public ResponseEntity<?> getAllFeatureFlagsForCompany(){
        UUID companyId = SecurityUtil.getCompanyId();
        return ResponseEntity.ok(featureFlagService.getAllFeatureFlags(companyId));
    }
    @GetMapping("/feature-flags/{featureName}")
    public ResponseEntity<FeatureFlagDTO> getFeatureFlagByName(@PathVariable String featureName) throws NoSuchFeatureFlagExistsException{
        UUID companyId = SecurityUtil.getCompanyId();
        FeatureFlag featureFlag = featureFlagService.getFeatureFlagByName(featureName, companyId);
        FeatureFlagDTO featureFlagDTO = FeatureFlagMapper.toFeatureFlagDTO(featureFlag);
        return ResponseEntity.ok(featureFlagDTO);
    }
    @DeleteMapping("/feature-flags/{featureName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FeatureFlagDTO> deleteFeatureFlag(@PathVariable String featureName) throws NoSuchFeatureFlagExistsException {
        UUID companyId = SecurityUtil.getCompanyId();
        FeatureFlag featureFlag = featureFlagService.deleteFeatureFlag(companyId,featureName);
        FeatureFlagDTO featureFlagDTO = FeatureFlagMapper.toFeatureFlagDTO(featureFlag);
        return ResponseEntity.ok(featureFlagDTO);
    }
}
