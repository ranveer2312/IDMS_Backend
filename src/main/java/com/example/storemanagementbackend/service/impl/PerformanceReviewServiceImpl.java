package com.example.storemanagementbackend.service.impl;
 
import com.example.storemanagementbackend.model.Employee;
import com.example.storemanagementbackend.model.PerformanceReview;
import com.example.storemanagementbackend.repository.EmployeeRepository; // Needed to validate employee existence
import com.example.storemanagementbackend.repository.PerformanceReviewRepository;
import com.example.storemanagementbackend.service.PerformanceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import java.util.List;
import java.util.NoSuchElementException;
import com.example.storemanagementbackend.dto.PerformanceReviewDTO;
 
/**
 * Implementation of the PerformanceReviewService interface.
 * Contains the business logic for managing performance reviews.
 */
@Service // Marks this class as a Spring service component
public class PerformanceReviewServiceImpl implements PerformanceReviewService {
 
    private final PerformanceReviewRepository performanceReviewRepository;
    private final EmployeeRepository employeeRepository; // Inject EmployeeRepository to check if employee exists
 
    @Autowired // Injects the necessary repository dependencies
    public PerformanceReviewServiceImpl(PerformanceReviewRepository performanceReviewRepository,
                                        EmployeeRepository employeeRepository) {
        this.performanceReviewRepository = performanceReviewRepository;
        this.employeeRepository = employeeRepository;
    }
 
    @Override
    @Transactional // Ensures the entire method runs as a single transaction
    public PerformanceReview createPerformanceReview(PerformanceReviewDTO performanceReviewDTO) {
        // Before creating a review, ensure the associated employee exists.
        // We retrieve the employee from the database to ensure we are working with a managed entity.
        String employeeIdString = performanceReviewDTO.getEmployeeId();
        if (employeeIdString == null || employeeIdString.isEmpty()) {
            throw new IllegalArgumentException("Employee ID must be provided to create a performance review.");
        }
 
        Employee employee = employeeRepository.findByEmployeeId(employeeIdString)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with employeeId: " + employeeIdString + ". Cannot create performance review."));
 
        PerformanceReview performanceReview = PerformanceReview.builder()
                .employee(employee)
                .reviewStatus(performanceReviewDTO.getReviewStatus() != null && !performanceReviewDTO.getReviewStatus().isEmpty() ? performanceReviewDTO.getReviewStatus() : "pending")
                .rating(performanceReviewDTO.getRating())
                .lastReviewDate(performanceReviewDTO.getLastReviewDate())
                .nextReviewDate(performanceReviewDTO.getNextReviewDate())
                .goals(performanceReviewDTO.getGoals())
                .feedback(performanceReviewDTO.getFeedback())
                .achievements(performanceReviewDTO.getAchievements())
                .reviewer(performanceReviewDTO.getReviewer())
                .build();
 
        return performanceReviewRepository.save(performanceReview);
    }
 
    @Override
    public PerformanceReview getPerformanceReviewById(Long id) {
        return performanceReviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Performance Review not found with id: " + id));
    }
 
    @Override
    public List<PerformanceReview> getAllPerformanceReviews() {
        return performanceReviewRepository.findAll();
    }
 
    @Override
    public List<PerformanceReview> getPerformanceReviewsByEmployeeId(String employeeId) {
        // Optionally, check if employee exists before fetching reviews for robustness.
        if (!employeeRepository.findByEmployeeId(employeeId).isPresent()) {
            throw new NoSuchElementException("Employee not found with ID: " + employeeId + ". Cannot retrieve performance reviews.");
        }
        return performanceReviewRepository.findByEmployee_EmployeeId(employeeId);
    }
 
    @Override
    @Transactional
    public PerformanceReview updatePerformanceReview(Long id, PerformanceReview performanceReviewDetails) {
        PerformanceReview existingReview = performanceReviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Performance Review not found with id: " + id));
 
        // Update fields based on the provided details.
        // Null checks ensure we only update fields that are explicitly provided in the request.
        if (performanceReviewDetails.getReviewStatus() != null) {
            existingReview.setReviewStatus(performanceReviewDetails.getReviewStatus());
        }
        if (performanceReviewDetails.getRating() != null) {
            existingReview.setRating(performanceReviewDetails.getRating());
        }
        if (performanceReviewDetails.getLastReviewDate() != null) {
            existingReview.setLastReviewDate(performanceReviewDetails.getLastReviewDate());
        }
        if (performanceReviewDetails.getNextReviewDate() != null) {
            existingReview.setNextReviewDate(performanceReviewDetails.getNextReviewDate());
        }
        if (performanceReviewDetails.getGoals() != null) {
            existingReview.setGoals(performanceReviewDetails.getGoals());
        }
        if (performanceReviewDetails.getFeedback() != null) {
            existingReview.setFeedback(performanceReviewDetails.getFeedback());
        }
        if (performanceReviewDetails.getAchievements() != null) {
            existingReview.setAchievements(performanceReviewDetails.getAchievements());
        }
        // Note: The employee associated with the review is typically not changed during an update.
        // If it needs to be changed, additional logic to validate and set the new employee would be required.
 
        return performanceReviewRepository.save(existingReview);
    }
 
    @Override
    @Transactional
    public void deletePerformanceReview(Long id) {
        if (!performanceReviewRepository.existsById(id)) {
            throw new NoSuchElementException("Performance Review not found with id: " + id);
        }
        performanceReviewRepository.deleteById(id);
    }
}
 
 