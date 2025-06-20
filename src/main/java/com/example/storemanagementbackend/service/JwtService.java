package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.entity.User;
import com.example.storemanagementbackend.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String generateToken(User user) {
        // TODO: Implement JWT token generation logic
        return "dummy-jwt-token-for-" + user.getUsername();
    }

    public String generateToken(Employee employee) {
        // TODO: Implement JWT token generation logic for Employee
        return "dummy-jwt-token-for-" + employee.getEmail();
    }
} 