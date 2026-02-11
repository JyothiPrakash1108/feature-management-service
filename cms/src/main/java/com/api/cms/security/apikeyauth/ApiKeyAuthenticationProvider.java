package com.api.cms.security.apikeyauth;

import com.api.cms.entity.Company;
import com.api.cms.repository.CompanyRepo;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {
    private CompanyRepo companyRepo;
    public ApiKeyAuthenticationProvider(CompanyRepo companyRepo){
        this.companyRepo = companyRepo;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       String apiKey = (String) authentication.getCredentials();
        Optional<Company> optionalCompany = companyRepo.findByApiKey(apiKey);
        System.out.println(":::::::LOG:::::::"+"company " + optionalCompany.get());
        if (!optionalCompany.isPresent()){
            throw new BadCredentialsException("Invalid Api key");
        }
        Company company = optionalCompany.get();
        ApiKeyPrincipal principal = new ApiKeyPrincipal(company.getCompanyId());
        return new ApiKeyAuthenticationToken(
                principal,
                List.of(new SimpleGrantedAuthority("ROLE_CLIENT"))
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthenticationToken.class.isAssignableFrom(authentication);
    }
}