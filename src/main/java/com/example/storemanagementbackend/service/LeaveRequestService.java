package com.example.storemanagementbackend.service;
 
import com.example.storemanagementbackend.dto.LeaveRequestDTO;
 
import java.time.LocalDate;
import java.util.List;
 
public interface LeaveRequestService {
 
    // Leave request methods
    LeaveRequestDTO submitLeaveRequest(LeaveRequestDTO dto);
    LeaveRequestDTO getLeaveRequestById(Long id);
    List<LeaveRequestDTO> getAllLeaveRequests();
    List<LeaveRequestDTO> getLeaveRequestsByEmployeeId(String employeeId);
    List<LeaveRequestDTO> getLeaveRequestsByStatus(String status);
    List<LeaveRequestDTO> getNonApprovedLeaveRequests();
    LeaveRequestDTO approveLeaveRequest(Long id, String hrComments);
    LeaveRequestDTO rejectLeaveRequest(Long id, String hrComments);
    void deleteLeaveRequest(Long id);
    int calculateDays(LocalDate startDate, LocalDate endDate);
    LeaveRequestDTO updateHoliday(Long id, LeaveRequestDTO dto);
}
 
 