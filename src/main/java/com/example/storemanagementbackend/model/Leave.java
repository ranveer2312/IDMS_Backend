package com.example.storemanagementbackend.model;
 
import jakarta.persistence.*;
import java.time.LocalDate;
 
@Entity
@Table(name = "leaves")
public class Leave {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String reason;
    private LocalDate StartDate;
    private LocalDate EndDate;
 
    // Getters and Setters
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getReason() {
        return reason;
    }
 
    public void setReason(String reason) {
        this.reason = reason;
    }
 
    public LocalDate getStartDate() {
        return StartDate;
    }
 
    public void setStartDate(LocalDate StartDate) {
        this.StartDate = StartDate;
    }
 
    public LocalDate getEndDate() {
        return EndDate;
    }
 
    public void setEndDate(LocalDate EndDate) {
        this.EndDate = EndDate;
    }
}
 
 