package com.example.storemanagementbackend.service;
 
import com.example.storemanagementbackend.model.LeaveRequest;
 
import java.time.LocalDate;
import java.util.List;
 
/**
 * Service interface for managing LeaveRequest entities.
 * Defines the business operations available for leave requests.
 */
public interface LeaveRequestService {
 
    /**
     * Submits a new leave request.
     * @param leaveRequest The LeaveRequest object containing request details.
     * @return The newly created LeaveRequest object.
     */
    LeaveRequest submitLeaveRequest(LeaveRequest leaveRequest);
 
    /**
     * Retrieves a single leave request by its unique ID.
     * @param id The ID of the leave request.
     * @return The LeaveRequest object if found, throws NoSuchElementException otherwise.
     */
    LeaveRequest getLeaveRequestById(Long id);
 
    /**
     * Retrieves all leave requests in the system.
     * @return A list of all LeaveRequest objects.
     */
    List<LeaveRequest> getAllLeaveRequests();
 
    /**
     * Retrieves all leave requests for a specific employee.
     * @param employeeId The ID of the employee.
     * @return A list of LeaveRequest objects for the specified employee.
     */
    List<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId);
 
    /**
     * Retrieves leave requests by their status.
     * @param status The status to filter by (e.g., "PENDING", "APPROVED", "REJECTED").
     * @return A list of LeaveRequest objects with the specified status.
     */
    List<LeaveRequest> getLeaveRequestsByStatus(String status);
 
    /**
     * Approves a pending leave request.
     * @param id The ID of the leave request to approve.
     * @param hrComments Optional comments from HR.
     * @return The updated LeaveRequest object with APPROVED status.
     * @throws ?NoSuchElementException if the leave request is not found.
     * @throws IllegalStateException if the leave request is not in PENDING status.
     */
    LeaveRequest approveLeaveRequest(Long id, String hrComments);
 
    /**
     * Rejects a pending leave request.
     * @param id The ID of the leave request to reject.
     * @param hrComments Optional comments from HR.
     * @return The updated LeaveRequest object with REJECTED status.
//     * @throws NoSuchElementException if the leave request is not found.
     * @throws IllegalStateException if the leave request is not in PENDING status.
     */
    LeaveRequest rejectLeaveRequest(Long id, String hrComments);
 
    /**
     * Deletes a leave request by its ID.
     * @param id The ID of the leave request to delete.
     * @throws /NoSuchElementException if the leave request with the given ID does not exist.
     */
    void deleteLeaveRequest(Long id);
 
    /**
     * Calculates the number of days between a start date and an end date (inclusive).
     * @param startDate The start date of the period.
     * @param endDate The end date of the period.
     * @return The number of days.
     */
    int calculateDays(LocalDate startDate, LocalDate endDate);
}
 