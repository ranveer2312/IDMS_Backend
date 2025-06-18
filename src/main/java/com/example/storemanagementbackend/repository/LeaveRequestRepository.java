package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long employeeId);
    List<LeaveRequest> findByStatus(String status);

    // ✅ New method for filtering by multiple statuses
    List<LeaveRequest> findByStatusIn(List<String> statuses);
}
