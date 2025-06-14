package com.example.storemanagementbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.storemanagementbackend.repository")
@EntityScan(basePackages = {"com.example.storemanagementbackend.entity", "com.example.storemanagementbackend.model"})
@EnableTransactionManagement
public class DatabaseConfig {
    // Configuration is handled by application.properties
} 