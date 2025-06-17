package com.example.storemanagementbackend.service.impl;
 
import com.example.storemanagementbackend.model.Employee;
import com.example.storemanagementbackend.model.LeaveRequest;
import com.example.storemanagementbackend.repository.EmployeeRepository; // To validate employee existence
import com.example.storemanagementbackend.repository.LeaveRequestRepository;
import com.example.storemanagementbackend.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
 
/**
 * Implementation of the LeaveRequestService interface.
 * Contains the business logic for managing leave requests.
 */
@Service // Marks this class as a Spring service component
public class LeaveRequestServiceImpl implements LeaveRequestService {
 
    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository; // To validate that the employee exists
 
    @Autowired // Injects the necessary repository dependencies
    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository, EmployeeRepository employeeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
    }
 
    @Override
    @Transactional // Ensures the entire method runs as a single transaction
    public LeaveRequest submitLeaveRequest(LeaveRequest leaveRequest) {
        // 1. Validate employee exists
        Long employeeId = leaveRequest.getEmployee() != null ? leaveRequest.getEmployee().getId() : null;
        if (employeeId == null) {
            throw new IllegalArgumentException("Employee ID must be provided for a leave request.");
        }
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with ID: " + employeeId + ". Cannot submit leave request."));
        leaveRequest.setEmployee(employee); // Set the managed Employee entity
 
        // 2. Validate dates
        if (leaveRequest.getStartDate() == null || leaveRequest.getEndDate() == null) {
            throw new IllegalArgumentException("Start date and End date cannot be null.");
        }
        if (leaveRequest.getStartDate().isAfter(leaveRequest.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
 
        // 3. Calculate number of days
        leaveRequest.setNumberOfDays(calculateDays(leaveRequest.getStartDate(), leaveRequest.getEndDate()));
 
        // 4. Set default status and request date
        if (leaveRequest.getStatus() == null || leaveRequest.getStatus().isEmpty()) {
            leaveRequest.setStatus("PENDING"); // Default status for new requests
        }
        if (leaveRequest.getRequestDate() == null) {
            leaveRequest.setRequestDate(LocalDate.now());
        }
 
        return leaveRequestRepository.save(leaveRequest);
    }
 
    @Override
    public LeaveRequest getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Leave Request not found with id: " + id));
    }
 
    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }
 
    @Override
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new NoSuchElementException("Employee not found with ID: " + employeeId + ". Cannot retrieve leave requests.");
        }
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }
 
    @Override
    public List<LeaveRequest> getLeaveRequestsByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            // If no status is provided, return all leaves. Or throw an error based on business rules.
            return leaveRequestRepository.findAll();
        }
        return leaveRequestRepository.findByStatus(status.toUpperCase()); // Ensure status is uppercase for consistency
    }
 
    @Override
    @Transactional
    public LeaveRequest approveLeaveRequest(Long id, String hrComments) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Leave Request not found with id: " + id));
 
        if (!"PENDING".equalsIgnoreCase(leaveRequest.getStatus())) {
            throw new IllegalStateException("Only PENDING leave requests can be approved. Current status: " + leaveRequest.getStatus());
        }
 
        leaveRequest.setStatus("APPROVED");
        leaveRequest.setHrComments(hrComments); // Set HR comments if provided
        return leaveRequestRepository.save(leaveRequest);
    }
 
    @Override
    @Transactional
    public LeaveRequest rejectLeaveRequest(Long id, String hrComments) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Leave Request not found with id: " + id));
 
        if (!"PENDING".equalsIgnoreCase(leaveRequest.getStatus())) {
            throw new IllegalStateException("Only PENDING leave requests can be rejected. Current status: " + leaveRequest.getStatus());
        }
 
        leaveRequest.setStatus("REJECTED");
        leaveRequest.setHrComments(hrComments); // Set HR comments if provided
        return leaveRequestRepository.save(leaveRequest);
    }
 
    @Override
    @Transactional
    public void deleteLeaveRequest(Long id) {
        if (!leaveRequestRepository.existsById(id)) {
            throw new NoSuchElementException("Leave Request not found with id: " + id);
        }
        leaveRequestRepository.deleteById(id);
    }
 
    @Override
    public int calculateDays(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date must be provided for day calculation.");
        }
        // Calculate inclusive days (end date - start date + 1)
        return (int) (ChronoUnit.DAYS.between(startDate, endDate) + 1);
    }
}