package com.example.storemanagementbackend.dto;

import lombok.Data;
import java.util.List;

@Data
public class EmployeeRegistrationRequest {
    private String employeeName;
    private String employeeId;
    private String email;
    private String password;
    private String phoneNumber;
    private List<String> roles;
} 