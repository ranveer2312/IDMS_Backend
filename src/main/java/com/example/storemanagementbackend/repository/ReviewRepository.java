package com.example.storemanagementbackend.repository;
 
import com.example.storemanagementbackend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
/**
 * Repository interface for Review entities.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Custom query methods can be added here if needed, e.g.:
    // List<Review> findByPerformanceId(Long performanceId);
    // List<Review> findByReviewer(String reviewer);
}
 