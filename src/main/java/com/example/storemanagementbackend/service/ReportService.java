package com.example.storemanagementbackend.service;
 
import com.example.storemanagementbackend.model.Report;
import com.example.storemanagementbackend.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class ReportService {
 
    @Autowired
    private ReportRepository reportRepository;
 
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
 
    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }
 
    public Report createReport(Report report) {
        // You can add business logic here before saving, e.g., validation
        // Ensure date is set if not already provided (e.g., from client)
        if (report.getDate() == null) {
            report.setDate(java.time.LocalDate.now());
        }
        if (report.getStatus() == null || report.getStatus().isEmpty()) {
            report.setStatus("draft"); // Default status if not provided
        }
        return reportRepository.save(report);
    }
 
    public Report updateReport(Long id, Report reportDetails) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isPresent()) {
            Report existingReport = optionalReport.get();
            existingReport.setType(reportDetails.getType());
            existingReport.setSubtype(reportDetails.getSubtype());
            existingReport.setTitle(reportDetails.getTitle());
            existingReport.setContent(reportDetails.getContent());
            existingReport.setDate(reportDetails.getDate());
            existingReport.setStatus(reportDetails.getStatus());
            existingReport.setSubmittedBy(reportDetails.getSubmittedBy());
            existingReport.setApprovedBy(reportDetails.getApprovedBy());
            existingReport.setApprovedDate(reportDetails.getApprovedDate());
            existingReport.setAttachments(reportDetails.getAttachments());
            return reportRepository.save(existingReport);
        } else {
            throw new RuntimeException("Report not found with id " + id);
        }
    }
 
    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
 
    public List<Report> getReportsByType(String type) {
        return reportRepository.findByType(type);
    }
 
    public List<Report> getReportsByTypeAndSubtype(String type, String subtype) {
        return reportRepository.findByTypeAndSubtype(type, subtype);
    }
    
    public List<Report> getReportsByEmployeeId(String employeeId) {
        return reportRepository.findBySubmittedBy(employeeId);
    }
}
 