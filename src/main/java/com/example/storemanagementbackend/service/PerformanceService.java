package com.example.storemanagementbackend.service;
 
import com.example.storemanagementbackend.model.Performance;
import com.example.storemanagementbackend.model.Project;
import com.example.storemanagementbackend.model.Review;
import com.example.storemanagementbackend.repository.PerformanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
 
/**
 * Service layer for managing Performance business logic.
 * Handles operations related to Performance entities, including their
 * associated projects and reviews.
 */
@Service
public class PerformanceService {
 
    @Autowired
    private PerformanceRepository performanceRepository;
 
    /**
     * Creates a new performance record.
     * Ensures that associated projects and reviews are correctly linked to the
     * performance before saving.
     * If promotionDate is null, sets it to the current date.
     *
     * @param performance The Performance object to create.
     * @return The saved Performance object.
     */
    @Transactional
    public Performance createPerformance(Performance performance) {
        // Set promotion date if not provided
        if (performance.getPromotionDate() == null) {
            performance.setPromotionDate(LocalDate.now());
        }
 
        // Set the back-reference for projects and reviews before saving
        if (performance.getProjects() != null) {
            performance.getProjects().forEach(project -> project.setPerformance(performance));
        }
        if (performance.getReviews() != null) {
            performance.getReviews().forEach(review -> review.setPerformance(performance));
        }
 
        return performanceRepository.save(performance);
    }
 
    /**
     * Retrieves a performance record by its ID.
     *
     * @param id The ID of the performance record.
     * @return An Optional containing the Performance object if found.
     */
    public Optional<Performance> getPerformanceById(Long id) {
        return performanceRepository.findById(id);
    }
 
    /**
     * Retrieves all performance records.
     *
     * @return A list of all Performance objects.
     */
    public List<Performance> getAllPerformances() {
        return performanceRepository.findAll();
    }
 
    /**
     * Updates an existing performance record.
     * Handles updating main performance details as well as its associated
     * achievements, skills, projects, and reviews.
     * Ensures proper linking of projects and reviews to the updated performance.
     *
     * @param id                 The ID of the performance record to update.
     * @param performanceDetails The Performance object with updated details.
     * @return The updated Performance object.
     * @throws RuntimeException if the performance record with the given ID is not
     *                          found.
     */
    @Transactional
    public Performance updatePerformance(Long id, Performance performanceDetails) {
        Optional<Performance> optionalPerformance = performanceRepository.findById(id);
        if (optionalPerformance.isPresent()) {
            Performance existingPerformance = optionalPerformance.get();
 
            // Update basic fields
            existingPerformance.setPosition(performanceDetails.getPosition());
            existingPerformance.setPromotionDate(performanceDetails.getPromotionDate());
            existingPerformance.setRating(performanceDetails.getRating());
 
            // Update ElementCollections (achievements and skills) by clearing and re-adding
            existingPerformance.getAchievements().clear();
            if (performanceDetails.getAchievements() != null) {
                existingPerformance.getAchievements().addAll(performanceDetails.getAchievements());
            }
 
            existingPerformance.getSkills().clear();
            if (performanceDetails.getSkills() != null) {
                existingPerformance.getSkills().addAll(performanceDetails.getSkills());
            }
 
            // Update OneToMany collections (projects and reviews)
            // Clear existing ones and add new ones, ensuring the back-reference is set.
            existingPerformance.getProjects().clear(); // This will trigger orphanRemoval for old projects
            if (performanceDetails.getProjects() != null) {
                performanceDetails.getProjects().forEach(project -> {
                    project.setPerformance(existingPerformance); // Set the back-reference
                    existingPerformance.getProjects().add(project);
                });
            }
 
            existingPerformance.getReviews().clear(); // This will trigger orphanRemoval for old reviews
            if (performanceDetails.getReviews() != null) {
                performanceDetails.getReviews().forEach(review -> {
                    review.setPerformance(existingPerformance); // Set the back-reference
                    existingPerformance.getReviews().add(review);
                });
            }
 
            return performanceRepository.save(existingPerformance);
        } else {
            throw new RuntimeException("Performance record not found with id " + id);
        }
    }
 
    /**
     * Deletes a performance record by its ID.
     *
     * @param id The ID of the performance record to delete.
     */
    public void deletePerformance(Long id) {
        performanceRepository.deleteById(id);
    }
}
 
 