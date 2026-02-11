package com.api.cms.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.cms.dto.company.CreateCompanyRequestDTO;
import com.api.cms.dto.company.CreateCompanyResponseDTO;
import com.api.cms.entity.Company;
import com.api.cms.entity.User;
import com.api.cms.exception.AdminNotFoundException;
import com.api.cms.exception.CompanyAlreadyExistsException;
import com.api.cms.exception.CompanyDoesNotExistException;
import com.api.cms.mapper.CompanyMapper;
import com.api.cms.service.CompanyService;
import com.api.cms.service.UserService;
import com.api.cms.util.SecurityUtil;

import jakarta.validation.Valid;




@RestController
public class CompanyController {
    private CompanyService companyService;
    private UserService userService;
    public CompanyController(CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }
    @PostMapping("/companies")
    public ResponseEntity<CreateCompanyResponseDTO> createCompany(@Valid @RequestBody CreateCompanyRequestDTO requestDTO) throws AdminNotFoundException, CompanyAlreadyExistsException {
        Company createdCompany = companyService.createCompany(requestDTO);
        User adminUser = userService.getAdminByCompanyId(createdCompany.getCompanyId());
        CreateCompanyResponseDTO responseDTO = CompanyMapper.toCreateCompanyResponseDTO(createdCompany, adminUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/companies")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateCompanyResponseDTO> getCompanyDetails() throws AdminNotFoundException, CompanyDoesNotExistException {
        UUID companyId = SecurityUtil.getCompanyId();
        Company company = companyService.getCompanyById(companyId);
        User adminUser = userService.getAdminByCompanyId(companyId);
        CreateCompanyResponseDTO responseDTO = CompanyMapper.toCreateCompanyResponseDTO(company, adminUser);
        return ResponseEntity.ok(responseDTO);
    }

}
