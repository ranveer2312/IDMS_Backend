package com.example.storemanagementbackend.model;
 
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
 
@Entity // Marks this class as a JPA entity, mapped to a database table
@Table(name = "leave_requests") // Specifies the table name in the database
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
public class LeaveRequest {
 
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures primary key generation strategy (auto-increment)
    private Long id; // Internal ID for the database
 
    @Column(name = "employee_id", nullable = false, columnDefinition = "VARCHAR(20)")
    private String employeeId;
 
    @Column(name = "employee_name", nullable = true)
    private String employeeName;
 
    @Column(nullable = false)
    private String leaveType; // e.g., "Annual Leave", "Medical Leave", "Personal Leave"
 
    @Column(nullable = false)
    private LocalDate startDate; // Start date of the leave
 
    @Column(nullable = false)
    private LocalDate endDate; // End date of the leave
 
    @Column(nullable = false)
    private Integer numberOfDays; // Calculated number of days for the leave
 
    private String status; // e.g., "PENDING", "APPROVED", "REJECTED"
 
    @Column(columnDefinition = "TEXT")
    private String reason; // Reason for the leave request
 
    @Column(columnDefinition = "TEXT")
    private String hrComments; // Comments added by HR during approval/rejection
 
    private LocalDate requestDate; // Date when the leave request was submitted
 
    @Column(name = "holiday_name")
    private String holidayName;
 
    @Column(name = "day")
    private String day;
 
    @Column(name = "type")
    private String type;
 
    @Column(name = "coverage")
    private String coverage;
}
 
 