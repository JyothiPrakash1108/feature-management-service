package com.api.cms.repository;

import com.api.cms.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.cms.entity.FeatureAccess;

@Repository
public interface FeatureAccessRepository extends JpaRepository<FeatureAccess, Long>  {
    boolean existsByFeatureNameAndRole(String featureName, Role role);
}
