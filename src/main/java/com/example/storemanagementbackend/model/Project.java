package com.example.storemanagementbackend.model;
 
import jakarta.persistence.*;
 
/**
 * Represents a project associated with a Performance record.
 */
@Entity
@Table(name = "projects")
public class Project {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY) // Many Projects can belong to One Performance
    @JoinColumn(name = "performance_id", nullable = false) // Foreign key column in the 'projects' table
    private Performance performance; // Link back to the Performance entity
 
    @Column(nullable = false)
    private String name;
 
    @Column(nullable = false)
    private String role;
 
    @Column(nullable = false)
    private String duration;
 
    @Column(nullable = false)
    private String status;
 
    // Constructors
    public Project() {
    }
 
    public Project(String name, String role, String duration, String status) {
        this.name = name;
        this.role = role;
        this.duration = duration;
        this.status = status;
    }
 
    // Getters and Setters (ensure these are present, either manually or via Lombok
    // @Data)
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public Performance getPerformance() {
        return performance;
    }
 
    public void setPerformance(Performance performance) {
        this.performance = performance;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }
 
    public String getDuration() {
        return duration;
    }
 
    public void setDuration(String duration) {
        this.duration = duration;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
}
 