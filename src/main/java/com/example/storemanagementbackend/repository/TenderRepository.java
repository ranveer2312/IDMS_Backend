package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.Tender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenderRepository extends JpaRepository<Tender, Long> {
} 