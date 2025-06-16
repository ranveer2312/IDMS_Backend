package com.example.storemanagementbackend.service;
 
import com.example.storemanagementbackend.model.Attendance;
import com.example.storemanagementbackend.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit; // Import for calculating time difference
import java.util.List;
import java.util.Optional;
 
@Service
public class AttendanceService {
 
    @Autowired  
    private AttendanceRepository attendanceRepository;
 
    /**
     * Marks an employee's attendance (sign-in or sign-out).
     * This method handles finding existing records for the day, updating them,
     * and calculating status and work hours.
     *
     * @param attendanceRequest The Attendance object sent from the frontend,
     *                          containing employeeId, date (optional), checkInTime
     *                          (optional),
     *                          and checkOutTime (optional).
     * @return The updated or newly created Attendance record.
     * @throws IllegalArgumentException if a sign-out is attempted without a prior
     *                                  sign-in.
     */
    public Attendance markAttendance(Attendance attendanceRequest) {
        // Ensure the date is set to today if not provided in the request
        LocalDate today = LocalDate.now();
        if (attendanceRequest.getDate() == null) {
            attendanceRequest.setDate(today);
        } else if (!attendanceRequest.getDate().equals(today)) {
            // Optional: If you want to enforce marking only for today's date,
            // you might throw an error or handle it differently.
            // For now, we'll proceed with the requested date but consider it for future.
            System.out.println("Warning: Attendance mark for a date other than today: " + attendanceRequest.getDate());
        }
 
        // Find existing attendance record for the employee and date
        Optional<Attendance> existingAttendanceOptional = attendanceRepository.findByEmployeeIdAndDate(
                attendanceRequest.getEmployeeId(), attendanceRequest.getDate());
 
        Attendance recordToSave;
 
        if (existingAttendanceOptional.isPresent()) {
            recordToSave = existingAttendanceOptional.get();
 
            // --- Handle Sign-In logic for existing record ---
            // Only update checkInTime if it's provided in the request and not already set
            // in the existing record
            if (attendanceRequest.getCheckInTime() != null && recordToSave.getCheckInTime() == null) {
                recordToSave.setCheckInTime(attendanceRequest.getCheckInTime());
                recordToSave.setStatus("present"); // Set status to present on sign-in
                recordToSave.setWorkHours(0.0); // Reset work hours upon sign-in
            }
 
            // --- Handle Sign-Out logic for existing record ---
            // Only update checkOutTime if it's provided, a checkInTime exists, and
            // checkOutTime is not already set
            if (attendanceRequest.getCheckOutTime() != null &&
                    recordToSave.getCheckInTime() != null && // Must have signed in to sign out
                    recordToSave.getCheckOutTime() == null) { // Only set if not already signed out
 
                recordToSave.setCheckOutTime(attendanceRequest.getCheckOutTime());
 
                // Calculate work hours and determine final status
                long minutesWorked = ChronoUnit.MINUTES.between(recordToSave.getCheckInTime(),
                        recordToSave.getCheckOutTime());
                double hoursWorked = minutesWorked / 60.0; // Convert minutes to hours
 
                // Apply business logic for status based on work hours
                if (hoursWorked < 4.5) { // Example: less than 4.5 hours is absent
                    recordToSave.setStatus("absent");
                } else if (hoursWorked < 9) { // Example: 4.5 to 9 hours is half-day
                    recordToSave.setStatus("half-day");
                } else { // Example: 9 or more hours is full present
                    recordToSave.setStatus("present");
                }
 
                // Set work hours, rounded to one decimal place
                recordToSave.setWorkHours(Double.valueOf(String.format("%.1f", hoursWorked)));
            } else if (attendanceRequest.getCheckOutTime() != null && recordToSave.getCheckInTime() == null) {
                // Scenario: Frontend sends a signOut request but no signIn was recorded for the
                // day
                // This could be an error or a case to handle (e.g., allow direct sign-out as
                // absent)
                // For now, let's throw an error to indicate invalid state
                throw new IllegalArgumentException("Cannot sign out without a prior sign-in for the day.");
            }
 
        } else {
            // --- Create a new record (for initial sign-in of the day) ---
            recordToSave = new Attendance();
            recordToSave.setEmployeeId(attendanceRequest.getEmployeeId());
            recordToSave.setDate(attendanceRequest.getDate()); // Use the date determined above (today)
            recordToSave.setWorkHours(0.0); // Default for new record
 
            if (attendanceRequest.getCheckInTime() != null) {
                recordToSave.setCheckInTime(attendanceRequest.getCheckInTime());
                recordToSave.setStatus("present"); // New record, signed in
            } else if (attendanceRequest.getCheckOutTime() != null) {
                // If only sign-out is sent for a new record, consider it an error or specific
                // absent case
                throw new IllegalArgumentException("Cannot sign out for a new attendance record without a sign-in.");
            } else {
                // If neither checkInTime nor checkOutTime is provided for a new record
                // This case should ideally not happen if frontend sends signIn/signOut
                recordToSave.setStatus("absent");
            }
        }
 
        // Final check: if after all logic status is still null but has check-in, set to
        // present
        if (recordToSave.getStatus() == null && recordToSave.getCheckInTime() != null) {
            recordToSave.setStatus("present");
        }
 
        // Save the attendance record to the database
        return attendanceRepository.save(recordToSave);
    }
 
    /**
     * Retrieves all attendance records for a specific employee, ordered by date
     * descending.
     *
     * @param employeeId The ID of the employee.
     * @return A list of Attendance records.
     */
    public List<Attendance> getAttendanceByEmployee(String employeeId) {
        // Corrected method call to match the repository method
        // findByEmployeeIdOrderByDateDesc
        return attendanceRepository.findByEmployeeIdOrderByDateDesc(employeeId);
    }
 
    /**
     * Retrieves all attendance records in the system.
     *
     * @return A list of all Attendance records.
     */
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
 
    /**
     * Deletes an attendance record by its ID.
     *
     * @param id The ID of the attendance record to delete.
     */
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
}
 