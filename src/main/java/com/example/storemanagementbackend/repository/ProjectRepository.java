// ProjectRepository.java
package com.example.storemanagementbackend.repository;
 
import com.example.storemanagementbackend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
/**
 * Repository interface for Project entities.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Custom query methods can be added here if needed, e.g.:
    // List<Project> findByPerformanceId(Long performanceId);
    // List<Project> findByStatus(String status);
}
 