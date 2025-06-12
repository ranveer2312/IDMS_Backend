package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
} 