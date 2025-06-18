package com.example.storemanagementbackend.repository;
 
import com.example.storemanagementbackend.model.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
/**
 * Spring Data JPA Repository for PerformanceReview entities.
 * Provides standard CRUD operations and custom query methods.
 */
@Repository // Marks this interface as a Spring Data JPA repository
public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long> {
    // JpaRepository provides basic CRUD operations: save, findById, findAll, delete, etc.
 
    /**
     * Finds all performance reviews associated with a specific employee ID.
     * Spring Data JPA automatically generates the query based on the method name.
     * @param employeeId The ID of the employee.
     * @return A list of PerformanceReview objects for the given employee.
     */
    List<PerformanceReview> findByEmployee_EmployeeId(String employeeId);
}
 
 
 