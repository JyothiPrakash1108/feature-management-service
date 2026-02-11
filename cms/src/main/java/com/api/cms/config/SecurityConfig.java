package com.api.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.cms.security.apikeyauth.ApiKeyAuthenticationFilter;
import com.api.cms.security.apikeyauth.ApiKeyAuthenticationProvider;
import com.api.cms.security.jwtauth.JWTAuthenticationFilter;
import com.api.cms.security.jwtauth.JwtAuthenticationProvider;
import com.api.cms.service.AuthService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    private ApiKeyAuthenticationProvider apiKeyAuthenticationProvider;
    private AuthService authService;
    private PasswordEncoder passwordEncoder;
    public SecurityConfig(JwtAuthenticationProvider jwtAuthenticationProvider, ApiKeyAuthenticationProvider apiKeyAuthenticationProvider, AuthService authService, PasswordEncoder passwordEncoder) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.apiKeyAuthenticationProvider = apiKeyAuthenticationProvider;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }
   


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .authenticationProvider(jwtAuthenticationProvider)
            .authenticationProvider(apiKeyAuthenticationProvider)
            .userDetailsService(authService)
            .passwordEncoder(passwordEncoder)
            .and()
            .build();
    }


     @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new JWTAuthenticationFilter(authenticationManager);
    }
    @Bean
    public ApiKeyAuthenticationFilter apiKeyAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new ApiKeyAuthenticationFilter(authenticationManager);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JWTAuthenticationFilter jwtAuthenticationFilter, ApiKeyAuthenticationFilter apiKeyAuthenticationFilter) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            .addFilterBefore(apiKeyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login", "/companies").permitAll()
                .requestMatchers("/client/**").hasRole("CLIENT")
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
