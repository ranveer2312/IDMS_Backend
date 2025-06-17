package com.example.storemanagementbackend.repository;
 
import com.example.storemanagementbackend.model.WeeklyActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
/**
 * Spring Data JPA Repository for WeeklyActivity entities.
 * Provides standard CRUD operations and custom query methods.
 */
@Repository // Marks this interface as a Spring Data JPA repository
public interface WeeklyActivityRepository extends JpaRepository<WeeklyActivity, Long> {
    // JpaRepository provides basic CRUD operations: save, findById, findAll, delete, etc.
 
 
    List<WeeklyActivity> findByStatus(String status);
 
 
    List<WeeklyActivity> findByCategory(String category);
 
 
    List<WeeklyActivity> findByAssignedTo(String assignedTo);
 
    // You can add more custom query methods here as needed, e.g., findByActivityDateBetween, findByTitleContaining
}
 
 
 