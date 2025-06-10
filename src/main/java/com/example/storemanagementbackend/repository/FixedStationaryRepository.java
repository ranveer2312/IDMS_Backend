package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.FixedStationary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedStationaryRepository extends JpaRepository<FixedStationary, Long> {
} 