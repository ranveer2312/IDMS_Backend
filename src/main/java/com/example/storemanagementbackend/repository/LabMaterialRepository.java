package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.LabMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabMaterialRepository extends JpaRepository<LabMaterial, Long> {
} 