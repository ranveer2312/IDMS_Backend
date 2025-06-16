package com.example.storemanagementbackend.model;
 
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;
// No need for @JsonProperty here if field names match JSON expectations
 
@Entity
@Table(name = "attendance") // Explicitly map to 'attendance' table
public class Attendance {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String employeeId;
    private LocalDate date;
 
    @Column(name = "check_in_time") // Maps to your MySQL 'check_in_time' column
    @JsonFormat(pattern = "HH:mm:ss") // Ensures "HH:mm:ss" format for JSON
    private LocalTime checkInTime;
 
    @Column(name = "check_out_time") // Maps to your MySQL 'check_out_time' column
    @JsonFormat(pattern = "HH:mm:ss") // Ensures "HH:mm:ss" format for JSON
    private LocalTime checkOutTime;
 
    private String status; // Assuming 'status' column in DB is 'status'
 
    // You might also want to add workHours to the entity if it's stored in DB
    @Column(name = "work_hours") // Assuming a work_hours column
    private Double workHours; // Use Double for nullable numeric values
 
    public Attendance() {
    }
 
    public Attendance(String employeeId, LocalDate date, LocalTime checkInTime, LocalTime checkOutTime, String status) {
        this.employeeId = employeeId;
        this.date = date;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.status = status;
        this.workHours = 0.0; // Initialize workHours
    }
 
    // --- Getters and Setters ---
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getEmployeeId() {
        return employeeId;
    }
 
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
 
    public LocalDate getDate() {
        return date;
    }
 
    public void setDate(LocalDate date) {
        this.date = date;
    }
 
    public LocalTime getCheckInTime() {
        return checkInTime;
    }
 
    public void setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
    }
 
    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }
 
    public void setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public Double getWorkHours() { // Getter for workHours
        return workHours;
    }
 
    public void setWorkHours(Double workHours) { // Setter for workHours
        this.workHours = workHours;
    }
}
 