package com.example.storemanagementbackend.config;

import com.example.storemanagementbackend.entity.Role;
import com.example.storemanagementbackend.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("ADMIN").isEmpty()) {
                roleRepository.save(Role.builder().name("ADMIN").build());
            }
            if (roleRepository.findByName("EMPLOYEE").isEmpty()) {
                roleRepository.save(Role.builder().name("EMPLOYEE").build());
            }
        };
    }
} 