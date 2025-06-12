package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long> {
} 