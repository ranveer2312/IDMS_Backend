package com.example.storemanagementbackend.dto;
 
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDTO {
    private Long id;
    private String employeeId;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberOfDays;
    private String status;
    private String reason;
    private String hrComments;
    private LocalDate requestDate;
    private String employeeName;
    private String holidayName;
    private String day;
    private String type;
    private String coverage;
    private String department;
    private String email;
    private String phoneNumber;
    private String bloodGroup;
    private String profilePhotoUrl;
    private String currentAddress;
    private String permanentAddress;
    private String position;
    private LocalDate joiningDate;
    private LocalDate relievingDate;
}
 