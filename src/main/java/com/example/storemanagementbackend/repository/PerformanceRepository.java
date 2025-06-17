package com.example.storemanagementbackend.repository;
 
import com.example.storemanagementbackend.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
/**
 * Repository interface for Performance entities.
 * Inherits basic CRUD operations from JpaRepository.
 */
@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    // Basic CRUD methods are inherited from JpaRepository
}
 