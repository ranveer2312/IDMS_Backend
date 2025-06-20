package com.example.storemanagementbackend.repository;
 
import com.example.storemanagementbackend.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
 
import java.util.List;
 
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(String employeeId);
    List<LeaveRequest> findByStatus(String status);
    List<LeaveRequest> findByStatusNot(String status);
}
 
 