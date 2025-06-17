package com.example.storemanagementbackend.model;
 
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
 
@Entity // Marks this class as a JPA entity, mapped to a database table
@Table(name = "employees") // Specifies the table name in the database
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
public class Employee {
 
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures primary key generation strategy (auto-increment)
    private Long id; // Internal ID for the database
 
    @Column(unique = true, nullable = false) // Ensures employeeId is unique and not null
    private String employeeId; // e.g., EMP001
 
    @Column(nullable = false)
    private String employeeName;
 
    @Column(unique = true, nullable = false) // Email should be unique for each employee
    private String email;
 
    private String phoneNumber;
 
    private String bloodGroup;
 
    private String profilePhotoUrl; // Stores the URL or path to the profile photo
 
    @Column(columnDefinition = "TEXT") // Use TEXT for potentially longer address strings
    private String currentAddress;
 
    @Column(columnDefinition = "TEXT")
    private String permanentAddress;
 
    // In a real application, passwords should NEVER be stored directly.
    // They should be hashed using strong algorithms (e.g., BCrypt) and handled by Spring Security.
    // For this example, we include it as a String, but be aware of security implications.
    private String password;
 
    private String position;
 
    private String department;
 
    private LocalDate joiningDate; // Stores the date of joining
 
    private LocalDate relievingDate; // Stores the date of relieving (can be null if still employed)
 
    private String status; // e.g., "Joining", "Active", "Relieving" - derived or explicitly set
}
 
 
 