package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.WaterBills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterBillsRepository extends JpaRepository<WaterBills, Long> {
} 