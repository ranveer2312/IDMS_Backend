package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.FinanceReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceReportRepository extends JpaRepository<FinanceReport, Long> {
} 