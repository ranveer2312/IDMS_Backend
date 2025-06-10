package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.System;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends JpaRepository<System, Long> {
} 