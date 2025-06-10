package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.LabInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabInventoryRepository extends JpaRepository<LabInventory, Long> {
} 