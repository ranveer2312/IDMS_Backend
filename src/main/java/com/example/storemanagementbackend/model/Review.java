package com.example.storemanagementbackend.model;
 
import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference; // Import for JSON serialization handling
 
/**
 * Represents a performance review associated with a Performance record.
 */
@Entity
@Table(name = "reviews") // Changed from "performance_reviews" to avoid conflict
public class Review {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY) // Many Reviews can belong to One Performance
    @JoinColumn(name = "performance_id", nullable = false) // Foreign key column in the 'performance_reviews' table
    @JsonBackReference // This side is ignored during serialization to prevent circular reference
    private Performance performance; // Link back to the Performance entity
 
    private LocalDate date;
 
    @Column(nullable = false)
    private Double rating;
 
    @Column(nullable = false, length = 1000)
    private String feedback;
 
    @Column(nullable = false)
    private String reviewer;
 
    // Constructors
    public Review() {
    }
 
    public Review(LocalDate date, Double rating, String feedback, String reviewer) {
        this.date = date;
        this.rating = rating;
        this.feedback = feedback;
        this.reviewer = reviewer;
    }
 
    // Getters and Setters
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public Performance getPerformance() {
        return performance;
    }
 
    public void setPerformance(Performance performance) {
        this.performance = performance;
    }
 
    public LocalDate getDate() {
        return date;
    }
 
    public void setDate(LocalDate date) {
        this.date = date;
    }
 
    public Double getRating() {
        return rating;
    }
 
    public void setRating(Double rating) {
        this.rating = rating;
    }
 
    public String getFeedback() {
        return feedback;
    }
 
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
 
    public String getReviewer() {
        return reviewer;
    }
 
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
 