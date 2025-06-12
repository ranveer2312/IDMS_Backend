package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.CompanyRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRegistrationRepository extends JpaRepository<CompanyRegistration, Long> {
} 