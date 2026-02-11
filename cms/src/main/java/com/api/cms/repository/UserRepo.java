package com.api.cms.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.cms.entity.User;
import com.api.cms.enums.Role;
@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    boolean existsByEmailAndCompanyId(String email, UUID companyId);

    Optional<User> findByCompanyIdAndRole(UUID companyId, Role role);

    Optional<User> findByEmail(String userEmail);
    
}
