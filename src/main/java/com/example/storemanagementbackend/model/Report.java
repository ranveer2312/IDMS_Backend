package com.example.storemanagementbackend.model;
 
import jakarta.persistence.*;
import java.time.LocalDate; // Use LocalDate for date field
 
@Entity
@Table(name = "reports") // Defines the table name in the database
public class Report {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments ID
    private Long id; // Use Long for primary key
 
    @Column(nullable = false) // Ensures the type column cannot be null
    private String type; // employee, visit, oem, customer, blueprint, projection, achievement
 
    private String subtype; // daily, weekly, monthly, yearly (only for employee reports)
 
    @Column(nullable = false)
    private String title;
 
    @Column(nullable = false, length = 2000) // Increased length for content
    private String content;
 
    @Column(nullable = false)
    private LocalDate date; // Stores only date, no time
 
    @Column(nullable = false)
    private String status; // draft, submitted, approved
 
    private String submittedBy;
 
    private String approvedBy;
 
    private LocalDate approvedDate;
 
    @ElementCollection // For collections of basic types
    @CollectionTable(name = "report_attachments", joinColumns = @JoinColumn(name = "report_id"))
    @Column(name = "attachment_url")
    private java.util.List<String> attachments; // Stores URLs or filenames of attachments
 
    // Constructors
    public Report() {
    }
 
    public Report(String type, String subtype, String title, String content, LocalDate date, String status,
            String submittedBy) {
        this.type = type;
        this.subtype = subtype;
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
        this.submittedBy = submittedBy;
    }
 
    // Getters and Setters
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
    public String getSubtype() {
        return subtype;
    }
 
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getContent() {
        return content;
    }
 
    public void setContent(String content) {
        this.content = content;
    }
 
    public LocalDate getDate() {
        return date;
    }
 
    public void setDate(LocalDate date) {
        this.date = date;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public String getSubmittedBy() {
        return submittedBy;
    }
 
    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }
 
    public String getApprovedBy() {
        return approvedBy;
    }
 
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
 
    public LocalDate getApprovedDate() {
        return approvedDate;
    }
 
    public void setApprovedDate(LocalDate approvedDate) {
        this.approvedDate = approvedDate;
    }
 
    public java.util.List<String> getAttachments() {
        return attachments;
    }
 
    public void setAttachments(java.util.List<String> attachments) {
        this.attachments = attachments;
    }
 
    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", submittedBy='" + submittedBy + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                ", approvedDate=" + approvedDate +
                ", attachments=" + attachments +
                '}';
    }
}
 