// Performance.java (Model)
package com.example.storemanagementbackend.model;
 
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference; // Import for JSON serialization handling
 
/**
 * Represents an employee's performance record.
 * Includes details like position, rating, achievements, skills, projects, and
 * reviews.
 * Uses Lombok for boilerplate code generation.
 */
@Entity
@Table(name = "employee_performance") // You can keep this table name or change to 'performance'
@Data // Generates equals, hashCode, and toString methods
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
@Builder // Generates a builder pattern for creating instances
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique ID for this performance record
 
    @Column(nullable = false)
    private String position;
 
    private LocalDate promotionDate;
 
    @Column(nullable = false)
    private Double rating;
 
    @ElementCollection // Stores a collection of simple types or embeddable objects
    @CollectionTable(name = "performance_achievements", joinColumns = @JoinColumn(name = "performance_id"))
    @Column(name = "achievement")
    private List<String> achievements = new ArrayList<>();
 
    @ElementCollection // Stores a collection of simple types or embeddable objects
    @CollectionTable(name = "performance_skills", joinColumns = @JoinColumn(name = "performance_id"))
    @Column(name = "skill_name")
    private List<String> skills = new ArrayList<>();
 
    @OneToMany(mappedBy = "performance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // This side manages the serialization, prevents circular reference
    private List<Project> projects = new ArrayList<>();
 
    @OneToMany(mappedBy = "performance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // This side manages the serialization, prevents circular reference
    private List<Review> reviews = new ArrayList<>();
 
    // Manual Getters and Setters (added to ensure compilation even if Lombok is not
    // fully configured)
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getPosition() {
        return position;
    }
 
    public void setPosition(String position) {
        this.position = position;
    }
 
    public LocalDate getPromotionDate() {
        return promotionDate;
    }
 
    public void setPromotionDate(LocalDate promotionDate) {
        this.promotionDate = promotionDate;
    }
 
    public Double getRating() {
        return rating;
    }
 
    public void setRating(Double rating) {
        this.rating = rating;
    }
 
    public List<String> getAchievements() {
        return achievements;
    }
 
    public void setAchievements(List<String> achievements) {
        this.achievements.clear();
        if (achievements != null) {
            this.achievements.addAll(achievements);
        }
    }
 
    public List<String> getSkills() {
        return skills;
    }
 
    public void setSkills(List<String> skills) {
        this.skills.clear();
        if (skills != null) {
            this.skills.addAll(skills);
        }
    }
 
    public List<Project> getProjects() {
        return projects;
    }
 
    public void setProjects(List<Project> projects) {
        this.projects.clear(); // Clear existing projects
        if (projects != null) {
            this.projects.addAll(projects);
            // Crucially, set the back-reference (Performance) on each Project
            projects.forEach(project -> project.setPerformance(this));
        }
    }
 
    public List<Review> getReviews() {
        return reviews;
    }
 
    public void setReviews(List<Review> reviews) {
        this.reviews.clear(); // Clear existing reviews
        if (reviews != null) {
            this.reviews.addAll(reviews);
            // Crucially, set the back-reference (Performance) on each Review
            reviews.forEach(review -> review.setPerformance(this));
        }
    }
}
 