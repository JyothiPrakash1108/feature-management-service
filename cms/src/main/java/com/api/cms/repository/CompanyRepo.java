package com.api.cms.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.cms.entity.Company;
@Repository
public interface CompanyRepo extends JpaRepository<Company, UUID> {
    Optional<Company> findByCompanyName(String companyName);
    Optional<Company> findByApiKey(String apiKey);
}
