package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.LeaveRequest;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestService {
    LeaveRequest submitLeaveRequest(LeaveRequest leaveRequest);
    LeaveRequest getLeaveRequestById(Long id);
    List<LeaveRequest> getAllLeaveRequests();
    List<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId);
    List<LeaveRequest> getLeaveRequestsByStatus(String status);

    // ✅ New Method
    List<LeaveRequest> getNonApprovedLeaveRequests();

    LeaveRequest approveLeaveRequest(Long id, String hrComments);
    LeaveRequest rejectLeaveRequest(Long id, String hrComments);
    void deleteLeaveRequest(Long id);
    int calculateDays(LocalDate startDate, LocalDate endDate);
}
