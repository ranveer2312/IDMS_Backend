package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.StationaryInventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationaryInventoryTransactionRepository extends JpaRepository<StationaryInventoryTransaction, Long> {
} 