package com.example.storemanagementbackend.controller; // Make sure package matches
 
import com.example.storemanagementbackend.model.Attendance;
import com.example.storemanagementbackend.repository.AttendanceRepository;
import com.example.storemanagementbackend.dto.AttendanceWithEmployeeDTO;
import com.example.storemanagementbackend.model.Employee;
import com.example.storemanagementbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
 
@RestController
@RequestMapping("/api/attendance")
// Add CORS if not already configured globally
@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceController {
 
    @Autowired
    private AttendanceRepository attendanceRepository;
 
    @Autowired
    private EmployeeRepository employeeRepository;
 
    // Endpoint to get all attendance records
    @GetMapping
    public ResponseEntity<List<AttendanceWithEmployeeDTO>> getAllAttendance() {
        List<Attendance> attendanceRecords = attendanceRepository.findAll();
        List<AttendanceWithEmployeeDTO> result = attendanceRecords.stream().map(att -> {
            AttendanceWithEmployeeDTO dto = new AttendanceWithEmployeeDTO();
            dto.setId(att.getId());
            dto.setEmployeeId(att.getEmployeeId());
            dto.setDate(att.getDate());
            dto.setCheckInTime(att.getCheckInTime());
            dto.setCheckOutTime(att.getCheckOutTime());
            dto.setStatus(att.getStatus());
            dto.setWorkHours(att.getWorkHours());
            // Fetch employee details
            Employee emp = employeeRepository.findByEmployeeId(att.getEmployeeId()).orElse(null);
            if (emp != null) {
                dto.setEmployeeName(emp.getEmployeeName());
                dto.setDepartment(emp.getDepartment());
            }
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
 
    // Endpoint to get attendance for a specific employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Attendance>> getEmployeeAttendance(@PathVariable String employeeId) {
        List<Attendance> attendanceRecords = attendanceRepository.findByEmployeeIdOrderByDateDesc(employeeId);
        return ResponseEntity.ok(attendanceRecords);
    }
 
    // Endpoint to mark attendance (sign-in or sign-out)
    @PostMapping("/mark")
    public ResponseEntity<Attendance> markAttendance(@RequestBody Attendance attendanceRequest) {
        LocalDate today = LocalDate.now();
 
        // Find existing record for today and employee
        // Use the employeeId from the attendanceRequest
        Optional<Attendance> existingAttendanceOpt = attendanceRepository.findByEmployeeIdAndDate(
                attendanceRequest.getEmployeeId(), today);
 
        Attendance recordToSave;
 
        if (existingAttendanceOpt.isPresent()) {
            recordToSave = existingAttendanceOpt.get();
        } else {
            // Create a new record if it doesn't exist for today
            recordToSave = new Attendance();
            recordToSave.setEmployeeId(attendanceRequest.getEmployeeId());
            recordToSave.setDate(today);
            recordToSave.setStatus("absent"); // Default status for a new day if no action taken
            recordToSave.setWorkHours(0.0);
        }
 
        // Handle Sign In Logic
        // Only set checkInTime if it's provided in the request and not already set
        if (attendanceRequest.getCheckInTime() != null && recordToSave.getCheckInTime() == null) {
            recordToSave.setCheckInTime(attendanceRequest.getCheckInTime());
            recordToSave.setStatus("present"); // Set status to present on sign-in
            // Work hours remain 0 until sign out
            recordToSave.setWorkHours(0.0);
        }
 
        // Handle Sign Out Logic
        // Only set checkOutTime if it's provided in the request, and checkInTime
        // exists, and checkOutTime is not already set
        if (attendanceRequest.getCheckOutTime() != null && recordToSave.getCheckInTime() != null
                && recordToSave.getCheckOutTime() == null) {
            recordToSave.setCheckOutTime(attendanceRequest.getCheckOutTime());
 
            // Calculate work hours
            if (recordToSave.getCheckInTime() != null && recordToSave.getCheckOutTime() != null) {
                long minutesWorked = ChronoUnit.MINUTES.between(recordToSave.getCheckInTime(),
                        recordToSave.getCheckOutTime());
                double hoursWorked = minutesWorked / 60.0; // Convert minutes to hours
 
                // Determine status based on work hours (align with frontend logic)
                if (hoursWorked < 4.5) { // Example threshold for absent
                    recordToSave.setStatus("absent");
                } else if (hoursWorked < 9) { // Example threshold for half-day
                    recordToSave.setStatus("half-day");
                } else {
                    recordToSave.setStatus("present");
                }
                recordToSave.setWorkHours(Double.valueOf(String.format("%.1f", hoursWorked))); // Round to one decimal
                                                                                               // place
            }
        }
 
        // Final check for status if somehow still null after sign-in attempt
        if (recordToSave.getStatus() == null && recordToSave.getCheckInTime() != null) {
            recordToSave.setStatus("present");
        }
 
        // Save the updated/new attendance record
        Attendance savedAttendance = attendanceRepository.save(recordToSave);
 
        // Return the saved object, which now has the updated ID, times, and status
        return ResponseEntity.ok(savedAttendance);
    }
}
 