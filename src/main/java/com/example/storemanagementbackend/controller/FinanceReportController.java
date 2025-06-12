package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.FinanceReport;
import com.example.storemanagementbackend.service.FinanceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financereports")
public class FinanceReportController {

    @Autowired
    private FinanceReportService financeReportService;

    @GetMapping
    public ResponseEntity<List<FinanceReport>> getAllFinanceReports() {
        List<FinanceReport> financeReports = financeReportService.getAllFinanceReports();
        return new ResponseEntity<>(financeReports, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinanceReport> getFinanceReportById(@PathVariable Long id) {
        FinanceReport financeReport = financeReportService.getFinanceReportById(id);
        if (financeReport != null) {
            return new ResponseEntity<>(financeReport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<FinanceReport> createFinanceReport(@RequestBody FinanceReport financeReport) {
        FinanceReport createdFinanceReport = financeReportService.createFinanceReport(financeReport);
        return new ResponseEntity<>(createdFinanceReport, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinanceReport> updateFinanceReport(@PathVariable Long id, @RequestBody FinanceReport financeReportDetails) {
        FinanceReport updatedFinanceReport = financeReportService.updateFinanceReport(id, financeReportDetails);
        if (updatedFinanceReport != null) {
            return new ResponseEntity<>(updatedFinanceReport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinanceReport(@PathVariable Long id) {
        financeReportService.deleteFinanceReport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 