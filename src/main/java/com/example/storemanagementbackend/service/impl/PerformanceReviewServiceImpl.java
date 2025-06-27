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
    public PerformanceReview updatePerformanceReview(Long id, PerformanceReviewDTO performanceReviewDTO) {
        PerformanceReview existingReview = performanceReviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Performance Review not found with id: " + id));

        // Fetch employee by employeeId from DTO
        if (performanceReviewDTO.getEmployeeId() != null) {
            Employee employee = employeeRepository.findByEmployeeId(performanceReviewDTO.getEmployeeId())
                .orElseThrow(() -> new NoSuchElementException("Employee not found with employeeId: " + performanceReviewDTO.getEmployeeId()));
            existingReview.setEmployee(employee);
        }
        if (performanceReviewDTO.getReviewStatus() != null) {
            existingReview.setReviewStatus(performanceReviewDTO.getReviewStatus());
        }
        if (performanceReviewDTO.getRating() != null) {
            existingReview.setRating(performanceReviewDTO.getRating());
        }
        if (performanceReviewDTO.getLastReviewDate() != null) {
            existingReview.setLastReviewDate(performanceReviewDTO.getLastReviewDate());
        }
        if (performanceReviewDTO.getNextReviewDate() != null) {
            existingReview.setNextReviewDate(performanceReviewDTO.getNextReviewDate());
        }
        if (performanceReviewDTO.getGoals() != null) {
            existingReview.setGoals(performanceReviewDTO.getGoals());
        }
        if (performanceReviewDTO.getFeedback() != null) {
            existingReview.setFeedback(performanceReviewDTO.getFeedback());
        }
        if (performanceReviewDTO.getAchievements() != null) {
            existingReview.setAchievements(performanceReviewDTO.getAchievements());
        }
        if (performanceReviewDTO.getReviewer() != null) {
            existingReview.setReviewer(performanceReviewDTO.getReviewer());
        }
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
 
 