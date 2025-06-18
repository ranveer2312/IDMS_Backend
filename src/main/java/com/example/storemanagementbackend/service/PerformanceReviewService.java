package com.example.storemanagementbackend.service;
 
import com.example.storemanagementbackend.model.PerformanceReview;
import com.example.storemanagementbackend.dto.PerformanceReviewDTO;
 
import java.util.List;
 
/**
 * Service interface for managing PerformanceReview entities.
 * Defines the business operations available for performance reviews.
 */
public interface PerformanceReviewService {
 
    /**
     * Creates a new performance review in the system.
     * @param performanceReviewDTO The PerformanceReviewDTO object containing review details.
     * @return The newly created PerformanceReview object.
     */
    PerformanceReview createPerformanceReview(PerformanceReviewDTO performanceReviewDTO);
 
    /**
     * Retrieves a single performance review by its unique ID.
     * @param id The ID of the performance review.
     * @return The PerformanceReview object if found, throws NoSuchElementException otherwise.
     */
    PerformanceReview getPerformanceReviewById(Long id);
 
    /**
     * Retrieves all performance reviews in the system.
     * @return A list of all PerformanceReview objects.
     */
    List<PerformanceReview> getAllPerformanceReviews();
 
    /**
     * Retrieves all performance reviews for a specific employee.
     * @param employeeId The ID of the employee whose reviews are to be retrieved.
     * @return A list of PerformanceReview objects for the specified employee.
     */
    List<PerformanceReview> getPerformanceReviewsByEmployeeId(String employeeId);
 
    /**
     * Updates an existing performance review identified by its ID.
     * @param id The ID of the performance review to update.
     * @param performanceReviewDetails The PerformanceReview object with updated details.
     * @return The updated PerformanceReview object if found, throws NoSuchElementException otherwise.
     */
    PerformanceReview updatePerformanceReview(Long id, PerformanceReview performanceReviewDetails);
 
    /**
     * Deletes a performance review by its ID.
     * @param id The ID of the performance review to delete.
//     * @throws NoSuchElementException if the performance review with the given ID does not exist.
     */
    void deletePerformanceReview(Long id);
}
 
 
 