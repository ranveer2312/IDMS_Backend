package com.example.storemanagementbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.storemanagementbackend.repository")
@EnableTransactionManagement
public class DatabaseConfig {
    // Configuration is handled by application.properties
} 