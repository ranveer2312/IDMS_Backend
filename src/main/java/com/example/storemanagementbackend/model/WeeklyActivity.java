package com.example.storemanagementbackend.model;
 
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalTime;
 
@Entity // Marks this class as a JPA entity, mapped to a database table
@Table(name = "weekly_activities") // Specifies the table name in the database
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
@Builder
public class WeeklyActivity {
 
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures primary key generation strategy (auto-increment)
    private Long id; // Internal ID for the database
 
    @Column(nullable = false)
    private String title; // e.g., "Team Building Workshop", "Performance Review Meeting"
 
    @Column(nullable = false)
    private String category; // e.g., "HR Team", "Marketing" (from "All Categories" dropdown, or "Team Building Workshop" category)
 
    @Column(nullable = false)
    private LocalDate activityDate; // Corresponds to the "Date" field
 
    @Column(nullable = false)
    private LocalTime activityTime; // Corresponds to the "Time" field (HH:MM)
 
    @Column(nullable = false)
    private String assignedTo; // e.g., "HR Manager", "HR Team"
 
    @Column(nullable = false)
    private String priority; // e.g., "High Priority", "Medium Priority" (from "Priority" dropdown)
 
    @Column(nullable = false)
    private String status; // e.g., "Pending", "In-progress", "Completed" (from "Status" dropdown)
 
    @Column(columnDefinition = "TEXT")
    private String description; // Detailed description of the activity
 
    @Column(columnDefinition = "TEXT")
    private String notes; // Additional notes for the activity
}
 
 
 