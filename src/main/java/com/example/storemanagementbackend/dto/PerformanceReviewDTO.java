package com.example.storemanagementbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReviewDTO {
    private Long id; // Optional, for updates or responses
    private String employeeId; // Using employeeId string for creation
    private String reviewStatus;
    private Double rating;
    private LocalDate lastReviewDate;
    private LocalDate nextReviewDate;
    private String goals;
    private String feedback;
    private String achievements;
} 