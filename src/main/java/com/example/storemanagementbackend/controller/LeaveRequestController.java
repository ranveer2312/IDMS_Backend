package com.example.storemanagementbackend.controller;
 
import com.example.storemanagementbackend.model.LeaveRequest;
import com.example.storemanagementbackend.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
import java.util.NoSuchElementException;
 
/**
 * REST Controller for managing LeaveRequest entities (employee leave applications).
 */
@RestController // Marks this class as a REST controller
@RequestMapping("/api/leave-requests") // Base path for all endpoints in this controller
@CrossOrigin(origins = "*") // Allows requests from any origin (for frontend development)
public class LeaveRequestController {
 
    private final LeaveRequestService leaveRequestService;
 
    @Autowired // Injects the LeaveRequestService dependency
    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }
 
    /**
     * Endpoint to submit a new leave request.
     * @param leaveRequest The LeaveRequest object to create.
     * @return ResponseEntity with the created leave request and HTTP status.
     */
    @PostMapping // Handles HTTP POST requests to /api/leave-requests
    public ResponseEntity<LeaveRequest> submitLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        try {
            LeaveRequest createdRequest = leaveRequestService.submitLeaveRequest(leaveRequest);
            return new ResponseEntity<>(createdRequest, HttpStatus.CREATED); // Returns 201 Created
        } catch (IllegalArgumentException | NoSuchElementException | IllegalStateException e) {
            // Catch validation errors or employee not found errors
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
 
    /**
     * Endpoint to get a single leave request by its unique ID.
     * @param id The ID of the leave request.
     * @return ResponseEntity with the leave request and HTTP status.
     */
    @GetMapping("/{id}") // Handles HTTP GET requests to /api/leave-requests/{id}
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable Long id) {
        try {
            LeaveRequest request = leaveRequestService.getLeaveRequestById(id);
            return new ResponseEntity<>(request, HttpStatus.OK); // Returns 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found
        }
    }
 
    /**
     * Endpoint to get all leave requests, with optional filtering by status or employee.
     * This combines several viewing needs into one flexible endpoint.
     * @param status Optional. Filters requests by their status (e.g., "APPROVED", "PENDING", "REJECTED").
     * @param employeeId Optional. Filters requests by the employee ID.
     * @return ResponseEntity with a list of leave requests and HTTP status.
     */
    @GetMapping // Handles HTTP GET requests to /api/leave-requests
    public ResponseEntity<List<LeaveRequest>> getFilteredLeaveRequests(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long employeeId) {
        List<LeaveRequest> leaveRequests;
        try {
            if (status != null && !status.isEmpty()) {
                leaveRequests = leaveRequestService.getLeaveRequestsByStatus(status);
            } else if (employeeId != null) {
                leaveRequests = leaveRequestService.getLeaveRequestsByEmployeeId(employeeId);
            } else {
                leaveRequests = leaveRequestService.getAllLeaveRequests();
            }
            return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // e.g., employeeId not found
        }
    }
 
    /**
     * Endpoint to approve a pending leave request.
     * @param id The ID of the leave request to approve.
     * @param hrComments Optional comments from HR.
     * @return ResponseEntity with the updated leave request and HTTP status.
     */
    @PutMapping("/{id}/approve") // Handles HTTP PUT requests to /api/leave-requests/{id}/approve
    public ResponseEntity<LeaveRequest> approveLeaveRequest(
            @PathVariable Long id,
            @RequestBody(required = false) String hrComments) { // hrComments can be optional
        try {
            LeaveRequest updatedRequest = leaveRequestService.approveLeaveRequest(id, hrComments);
            return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT); // 409 Conflict if not PENDING
        }
    }
 
    /**
     * Endpoint to reject a pending leave request.
     * @param id The ID of the leave request to reject.
     * @param hrComments Optional comments from HR.
     * @return ResponseEntity with the updated leave request and HTTP status.
     */
    @PutMapping("/{id}/reject") // Handles HTTP PUT requests to /api/leave-requests/{id}/reject
    public ResponseEntity<LeaveRequest> rejectLeaveRequest(
            @PathVariable Long id,
            @RequestBody(required = false) String hrComments) { // hrComments can be optional
        try {
            LeaveRequest updatedRequest = leaveRequestService.rejectLeaveRequest(id, hrComments);
            return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT); // 409 Conflict if not PENDING
        }
    }
 
    /**
     * Endpoint to delete a leave request.
     * @param id The ID of the leave request to delete.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping("/{id}") // Handles HTTP DELETE requests to /api/leave-requests/{id}
    public ResponseEntity<HttpStatus> deleteLeaveRequest(@PathVariable Long id) {
        try {
            leaveRequestService.deleteLeaveRequest(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found
        }
    }
}
 