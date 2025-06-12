package com.example.storemanagementbackend.service.impl;

import com.example.storemanagementbackend.model.FinanceReport;
import com.example.storemanagementbackend.repository.FinanceReportRepository;
import com.example.storemanagementbackend.service.FinanceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceReportServiceImpl implements FinanceReportService {

    @Autowired
    private FinanceReportRepository financeReportRepository;

    @Override
    public List<FinanceReport> getAllFinanceReports() {
        return financeReportRepository.findAll();
    }

    @Override
    public FinanceReport getFinanceReportById(Long id) {
        Optional<FinanceReport> financeReport = financeReportRepository.findById(id);
        return financeReport.orElse(null);
    }

    @Override
    public FinanceReport createFinanceReport(FinanceReport financeReport) {
        return financeReportRepository.save(financeReport);
    }

    @Override
    public FinanceReport updateFinanceReport(Long id, FinanceReport financeReportDetails) {
        FinanceReport financeReport = financeReportRepository.findById(id).orElse(null);
        if (financeReport != null) {
            financeReport.setReportType(financeReportDetails.getReportType());
            financeReport.setPeriod(financeReportDetails.getPeriod());
            financeReport.setDate(financeReportDetails.getDate());
            financeReport.setStatus(financeReportDetails.getStatus());
            financeReport.setAmount(financeReportDetails.getAmount());
            financeReport.setDepartment(financeReportDetails.getDepartment());
            financeReport.setPreparedBy(financeReportDetails.getPreparedBy());
            return financeReportRepository.save(financeReport);
        }
        return null;
    }

    @Override
    public void deleteFinanceReport(Long id) {
        financeReportRepository.deleteById(id);
    }
} 