package com.example.storemanagementbackend.dto;

import com.example.storemanagementbackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String username;
    private String email;
    private Set<Role> roles;
} 