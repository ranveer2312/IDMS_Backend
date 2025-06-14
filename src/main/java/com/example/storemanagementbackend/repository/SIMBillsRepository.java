package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.SIMBills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SIMBillsRepository extends JpaRepository<SIMBills, Long> {
} 