package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.dto.AuthRequest;
import com.example.storemanagementbackend.dto.AuthResponse;
import com.example.storemanagementbackend.dto.RegisterRequest;
import com.example.storemanagementbackend.model.Employee;
import com.example.storemanagementbackend.repository.EmployeeRepository;
import com.example.storemanagementbackend.repository.UserRepository;
import com.example.storemanagementbackend.service.AuthService;
import com.example.storemanagementbackend.service.JwtService;
import com.example.storemanagementbackend.service.EmailService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.Map;

import com.example.storemanagementbackend.entity.User;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final EmailService emailService;

    // ✅ Enable CORS for frontend hosted on Vercel
    @CrossOrigin(origins = "https://idmsproject.vercel.app")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @CrossOrigin(origins = "https://idmsproject.vercel.app")
    @PostMapping("/login")
    public ResponseEntity<?> unifiedLogin(@RequestBody AuthRequest request) {
        // 1. Try User table (admin, HR, etc.)
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtService.generateToken(user);
            AuthResponse response = new AuthResponse();
            response.setEmail(user.getEmail());
            response.setRoles(user.getRoles().stream().map(r -> r.getName()).toList());
            response.setToken(token);
            return ResponseEntity.ok(response);
        }

        // 2. Try Employee table
        Employee employee = employeeRepository.findByEmail(request.getEmail()).orElse(null);
        if (employee != null && passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            String token = jwtService.generateToken(employee); // Use the same method for Employee
            AuthResponse response = new AuthResponse();
            response.setEmployeeId(employee.getEmployeeId());
            response.setEmployeeName(employee.getEmployeeName());
            response.setEmail(employee.getEmail());
            response.setDepartment(employee.getDepartment());
            response.setPosition(employee.getPosition());
            response.setStatus(employee.getStatus());
            response.setRoles(List.of("EMPLOYEE"));
            response.setToken(token);
            return ResponseEntity.ok(response);
        }

        // 3. If neither, return error
        return ResponseEntity.status(401).body("Invalid email or password");
    }

    @CrossOrigin(origins = "https://idmsproject.vercel.app")
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String otp = String.format("%06d", new Random().nextInt(999999));
        emailService.sendOtpEmail(email, otp);
        return ResponseEntity.ok("Password reset instructions sent to your email.");
    }

    @CrossOrigin(origins = "https://idmsproject.vercel.app")
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String otp = body.get("otp");
        String newPassword = body.get("newPassword");

        if (otp == null || otp.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid or missing OTP");
        }

        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return ResponseEntity.ok("Password reset successful for user.");
        }

        Employee employee = employeeRepository.findByEmail(email).orElse(null);
        if (employee != null) {
            employee.setPassword(passwordEncoder.encode(newPassword));
            employeeRepository.save(employee);
            return ResponseEntity.ok("Password reset successful for employee.");
        }

        return ResponseEntity.status(404).body("Email not found");
    }
}
