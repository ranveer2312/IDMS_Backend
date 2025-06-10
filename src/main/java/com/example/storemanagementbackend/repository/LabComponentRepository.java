package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.LabComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabComponentRepository extends JpaRepository<LabComponent, Long> {
} 