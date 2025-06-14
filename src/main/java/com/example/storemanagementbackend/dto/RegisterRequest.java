package com.example.storemanagementbackend.dto;

import com.example.storemanagementbackend.entity.Role;
import lombok.Data;
import java.util.Set;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;
} 