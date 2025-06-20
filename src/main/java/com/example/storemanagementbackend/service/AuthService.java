package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.dto.AuthRequest;
import com.example.storemanagementbackend.dto.AuthResponse;
import com.example.storemanagementbackend.dto.RegisterRequest;
import com.example.storemanagementbackend.entity.Role;
import com.example.storemanagementbackend.entity.User;
import com.example.storemanagementbackend.repository.RoleRepository;
import com.example.storemanagementbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Set<String> ALLOWED_ROLES = Set.of(
        "ADMIN", "STORE", "DATA_MANAGER", "HR", "FINANCE_MANAGER"
    );

    public AuthResponse register(RegisterRequest request) {
        // Only allow registration for allowed roles
        for (String roleName : request.getRoles()) {
            if (!ALLOWED_ROLES.contains(roleName)) {
                throw new RuntimeException("Registration not allowed for role: " + roleName);
            }
            // Check if any user already has this role
            Role role = roleRepository.findByName(roleName).orElse(null);
            if (role != null) {
                List<User> usersWithRole = userRepository.findAll().stream()
                    .filter(u -> u.getRoles().stream().anyMatch(r -> r.getName().equals(roleName)))
                    .collect(Collectors.toList());
                if (!usersWithRole.isEmpty()) {
                    throw new RuntimeException(roleName + " already has an account");
                }
            }
        }
        Set<Role> roles = request.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseGet(() -> roleRepository.save(Role.builder().name(roleName).build())))
                .collect(Collectors.toSet());

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .roles(roles)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .token(jwtToken)
                .build();
    }
} 