package com.example.storemanagementbackend.repository;
 
import com.example.storemanagementbackend.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
/**
 * Spring Data JPA Repository for LeaveRequest entities.
 * Provides standard CRUD operations and custom query methods.
 */
@Repository // Marks this interface as a Spring Data JPA repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    // JpaRepository provides basic CRUD operations: save, findById, findAll, delete, etc.
 
    /**
     * Finds all leave requests for a specific employee.
     * @param employeeId The ID of the employee.
     * @return A list of LeaveRequest objects for the given employee.
     */
    List<LeaveRequest> findByEmployeeId(Long employeeId);
 
    /**
     * Finds all leave requests with a specific status.
     * @param status The status of the leave request (e.g., "PENDING", "APPROVED", "REJECTED").
     * @return A list of LeaveRequest objects matching the status.
     */
    List<LeaveRequest> findByStatus(String status);
}
 