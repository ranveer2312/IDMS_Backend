package com.example.storemanagementbackend.controller;
 
import com.example.storemanagementbackend.model.Report;
import com.example.storemanagementbackend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
 
@RestController
@RequestMapping("/api/reports") // Base URL for all report-related endpoints
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from your Next.js frontend
public class ReportController {
 
    @Autowired
    private ReportService reportService;
 
    // GET all reports or filter by type/subtype
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String subtype) {
 
        List<Report> reports;
        if (type != null && !type.equalsIgnoreCase("all")) {
            if (type.equalsIgnoreCase("employee") && subtype != null && !subtype.equalsIgnoreCase("all")) {
                reports = reportService.getReportsByTypeAndSubtype(type, subtype);
            } else {
                reports = reportService.getReportsByType(type);
            }
        } else {
            reports = reportService.getAllReports();
        }
        return ResponseEntity.ok(reports);
    }
 
    // GET a report by ID
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        Optional<Report> report = reportService.getReportById(id);
        return report.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
 
    // CREATE a new report
    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        // Set current date if not provided in the request body
        if (report.getDate() == null) {
            report.setDate(LocalDate.now());
        }
        // Set default status if not provided
        if (report.getStatus() == null || report.getStatus().isEmpty()) {
            report.setStatus("draft"); // Or 'submitted' depending on your default flow
        }
        Report createdReport = reportService.createReport(report);
        return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
    }
 
    // UPDATE an existing report
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report reportDetails) {
        try {
            Report updatedReport = reportService.updateReport(id, reportDetails);
            return ResponseEntity.ok(updatedReport);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
 
    // DELETE a report
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        try {
            reportService.deleteReport(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Handle case where report might not exist for deletion
            return ResponseEntity.notFound().build();
        }
    }
}
 