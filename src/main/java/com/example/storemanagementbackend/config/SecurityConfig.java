package com.example.storemanagementbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for testing APIs via Postman
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Allow all requests for testing
            )
            .httpBasic(); // Optional: for basic auth testing
        return http.build();
    }
}
