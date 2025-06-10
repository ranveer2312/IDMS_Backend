package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.LabInventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabInventoryTransactionRepository extends JpaRepository<LabInventoryTransaction, Long> {
} 