package com.example.storemanagementbackend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceWithEmployeeDTO {
    private Long id;
    private String employeeId;
    private String employeeName;
    private String department;
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private String status;
    private Double workHours;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getCheckInTime() { return checkInTime; }
    public void setCheckInTime(LocalTime checkInTime) { this.checkInTime = checkInTime; }
    public LocalTime getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(LocalTime checkOutTime) { this.checkOutTime = checkOutTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getWorkHours() { return workHours; }
    public void setWorkHours(Double workHours) { this.workHours = workHours; }
} 