package com.example.storemanagementbackend.model;
 
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;
 
@Entity // Marks this class as a JPA entity, mapped to a database table
@Table(name = "performance_reviews") // Specifies the table name in the database
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
@Builder
public class PerformanceReview {
 
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures primary key generation strategy (auto-increment)
    private Long id; // Internal ID for the database
 
    // ManyToOne relationship: Many performance reviews can belong to one employee
    // FetchType.LAZY means the Employee object will only be loaded from the DB when explicitly accessed.
    // @JoinColumn specifies the foreign key column in the 'performance_reviews' table.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // The Employee this performance review is associated with
 
    @Column(nullable = false)
    private String reviewStatus; // e.g., "completed", "pending", "overdue"
    private Double rating; // e.g., 4.5 (can be null if review is pending)
    @Column(nullable = false)
    private LocalDate lastReviewDate; // Date when the last review was conducted
    @Column(nullable = false)
    private LocalDate nextReviewDate; // Date when the next review is scheduled
 
    @Column(columnDefinition = "TEXT") // Use TEXT for potentially longer string fields like goals
    private String goals; // Goals set for the employee for the review period
 
    @Column(columnDefinition = "TEXT")
    private String feedback; // Feedback provided during the review
 
    @Column(columnDefinition = "TEXT")
    private String achievements; // Key achievements during the review period
}
 
 