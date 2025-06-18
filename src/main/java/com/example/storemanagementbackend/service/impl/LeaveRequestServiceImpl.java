package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.LeaveRequest;
import com.example.storemanagementbackend.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Override
    public LeaveRequest submitLeaveRequest(LeaveRequest leaveRequest) {
        leaveRequest.setStatus("PENDING");
        leaveRequest.setNumberOfDays(calculateDays(leaveRequest.getStartDate(), leaveRequest.getEndDate()));
        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public LeaveRequest getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Leave request not found"));
    }

    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    @Override
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<LeaveRequest> getLeaveRequestsByStatus(String status) {
        return leaveRequestRepository.findByStatus(status);
    }

    // ✅ New Method
    @Override
    public List<LeaveRequest> getNonApprovedLeaveRequests() {
        return leaveRequestRepository.findByStatusIn(List.of("PENDING", "REJECTED"));
    }

    @Override
    public LeaveRequest approveLeaveRequest(Long id, String hrComments) {
        LeaveRequest request = getLeaveRequestById(id);
        if (!request.getStatus().equalsIgnoreCase("PENDING")) {
            throw new IllegalStateException("Only PENDING requests can be approved");
        }
        request.setStatus("APPROVED");
        request.setHrComments(hrComments);
        return leaveRequestRepository.save(request);
    }

    @Override
    public LeaveRequest rejectLeaveRequest(Long id, String hrComments) {
        LeaveRequest request = getLeaveRequestById(id);
        if (!request.getStatus().equalsIgnoreCase("PENDING")) {
            throw new IllegalStateException("Only PENDING requests can be rejected");
        }
        request.setStatus("REJECTED");
        request.setHrComments(hrComments);
        return leaveRequestRepository.save(request);
    }

    @Override
    public void deleteLeaveRequest(Long id) {
        LeaveRequest request = getLeaveRequestById(id);
        leaveRequestRepository.delete(request);
    }

    @Override
    public int calculateDays(java.time.LocalDate startDate, java.time.LocalDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate, endDate) + 1; // inclusive
    }
}
