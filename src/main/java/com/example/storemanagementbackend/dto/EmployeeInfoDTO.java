package com.example.storemanagementbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfoDTO {
    private String employeeId;
    private String employeeName;
    private String department;
    private String email;
} 