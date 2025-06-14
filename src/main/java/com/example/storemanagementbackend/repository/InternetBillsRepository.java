package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.InternetBills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternetBillsRepository extends JpaRepository<InternetBills, Long> {
} 