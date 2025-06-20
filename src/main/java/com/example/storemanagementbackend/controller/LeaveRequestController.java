package com.example.storemanagementbackend.controller;
 
import com.example.storemanagementbackend.dto.LeaveRequestDTO;
import com.example.storemanagementbackend.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Adjust for your frontend URL
public class LeaveRequestController {
 
    @Autowired
    private LeaveRequestService leaveRequestService;
 
    // Leave Request Endpoints (Employee)
    @PostMapping("/leave-requests/employee")
    public ResponseEntity<LeaveRequestDTO> submitLeaveRequest(@RequestBody LeaveRequestDTO dto) {
        LeaveRequestDTO saved = leaveRequestService.submitLeaveRequest(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
 
    @GetMapping("/leave-requests/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequestDTO>> getLeaveRequestsByEmployee(@PathVariable String employeeId) {
        List<LeaveRequestDTO> requests = leaveRequestService.getLeaveRequestsByEmployeeId(employeeId);
        return ResponseEntity.ok(requests);
    }
 
    // Leave Request Endpoints (HR/Admin)
    @GetMapping("/leave-requests/hr/all")
    public ResponseEntity<List<LeaveRequestDTO>> getAllLeaveRequests() {
        return ResponseEntity.ok(leaveRequestService.getAllLeaveRequests());
    }
 
    @GetMapping("/leave-requests/hr/status/{status}")
    public ResponseEntity<List<LeaveRequestDTO>> getLeaveRequestsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(leaveRequestService.getLeaveRequestsByStatus(status));
    }
 
    @GetMapping("/leave-requests/non-approved")
    public ResponseEntity<List<LeaveRequestDTO>> getNonApprovedLeaveRequests() {
        return ResponseEntity.ok(leaveRequestService.getNonApprovedLeaveRequests());
    }
 
    @PutMapping("/leave-requests/hr/{id}/approve")
    public ResponseEntity<LeaveRequestDTO> approveLeaveRequest(
            @PathVariable Long id,
            @RequestParam(required = false) String hrComments) {
        LeaveRequestDTO updated = leaveRequestService.approveLeaveRequest(id, hrComments);
        return ResponseEntity.ok(updated);
    }
 
    @PutMapping("/leave-requests/hr/{id}/reject")
    public ResponseEntity<LeaveRequestDTO> rejectLeaveRequest(
            @PathVariable Long id,
            @RequestParam(required = false) String hrComments) {
        LeaveRequestDTO updated = leaveRequestService.rejectLeaveRequest(id, hrComments);
        return ResponseEntity.ok(updated);
    }
 
    @DeleteMapping("/leave-requests/{id}")
    public ResponseEntity<Void> deleteLeaveRequest(@PathVariable Long id) {
        leaveRequestService.deleteLeaveRequest(id);
        return ResponseEntity.noContent().build();
    }
 
    // --- Holiday Leave Management (HR) ---
    @GetMapping("/holidays")
    public List<LeaveRequestDTO> getAllHolidays() {
        return leaveRequestService.getLeaveRequestsByStatus("HOLIDAY");
    }
 
    @GetMapping("/holidays/{id}")
    public LeaveRequestDTO getHolidayById(@PathVariable Long id) {
        return leaveRequestService.getLeaveRequestById(id);
    }
 
    @PostMapping("/holidays")
    public LeaveRequestDTO addHoliday(@RequestBody LeaveRequestDTO dto) {
        dto.setStatus("HOLIDAY");
        return leaveRequestService.submitLeaveRequest(dto);
    }
 
    @PutMapping("/holidays/{id}")
    public LeaveRequestDTO updateHoliday(@PathVariable Long id, @RequestBody LeaveRequestDTO dto) {
        dto.setStatus("HOLIDAY");
        return leaveRequestService.updateHoliday(id, dto);
    }
 
    @DeleteMapping("/holidays/{id}")
    public void deleteHoliday(@PathVariable Long id) {
        leaveRequestService.deleteLeaveRequest(id);
    }
}
 
 