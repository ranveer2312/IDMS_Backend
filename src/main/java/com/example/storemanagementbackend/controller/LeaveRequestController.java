package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.LeaveRequest;
import com.example.storemanagementbackend.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for HR to manage employee leave requests.
 */
@RestController
@RequestMapping("/api/leave-requests")
@CrossOrigin(origins = "*")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    /**
     * Get a specific leave request by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable Long id) {
        LeaveRequest request = leaveRequestService.getLeaveRequestById(id);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    /**
     * Get all leave requests.
     */
    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequests() {
        List<LeaveRequest> requests = leaveRequestService.getAllLeaveRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    /**
     * Get leave requests by status (e.g., PENDING, APPROVED, REJECTED).
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<LeaveRequest>> getLeaveRequestsByStatus(@PathVariable String status) {
        List<LeaveRequest> requests = leaveRequestService.getLeaveRequestsByStatus(status);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    /**
     * ✅ Get all leave requests that are NOT APPROVED (i.e., PENDING or REJECTED).
     */
    @GetMapping("/non-approved")
    public ResponseEntity<List<LeaveRequest>> getNonApprovedLeaveRequests() {
        List<LeaveRequest> requests = leaveRequestService.getNonApprovedLeaveRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    /**
     * ✅ Approve a pending leave request.
     */
    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveRequest> approveLeaveRequest(
            @PathVariable Long id,
            @RequestParam(required = false) String hrComments) {
        LeaveRequest updated = leaveRequestService.approveLeaveRequest(id, hrComments);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /**
     * ✅ Reject a pending leave request.
     */
    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveRequest> rejectLeaveRequest(
            @PathVariable Long id,
            @RequestParam(required = false) String hrComments) {
        LeaveRequest updated = leaveRequestService.rejectLeaveRequest(id, hrComments);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /**
     * Delete a leave request.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveRequest(@PathVariable Long id) {
        leaveRequestService.deleteLeaveRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
