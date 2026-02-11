package com.api.cms.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.cms.entity.FeatureFlag;
@Repository
public interface FeatureFlagRepo extends JpaRepository<FeatureFlag, UUID> {
    Optional<FeatureFlag> findByCompanyIdAndName(UUID companyId, String name);

    List<FeatureFlag> findAllByCompanyId(UUID companyId);
}
