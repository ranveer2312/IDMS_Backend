package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.FinanceReport;
import java.util.List;

public interface FinanceReportService {
    List<FinanceReport> getAllFinanceReports();
    FinanceReport getFinanceReportById(Long id);
    FinanceReport createFinanceReport(FinanceReport financeReport);
    FinanceReport updateFinanceReport(Long id, FinanceReport financeReportDetails);
    void deleteFinanceReport(Long id);
} 