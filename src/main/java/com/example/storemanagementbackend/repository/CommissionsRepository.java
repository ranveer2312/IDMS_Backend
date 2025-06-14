package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionsRepository extends JpaRepository<Commission, Long> {
} 