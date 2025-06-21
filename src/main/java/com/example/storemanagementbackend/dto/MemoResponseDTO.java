package com.example.storemanagementbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoResponseDTO {
    private Long id;
    private String title;
    private String meetingType;
    private LocalDate meetingDate;
    private String priority;
    private String content;
    private String sentBy;
    private String sentByName;
    private List<String> recipientEmployeeIds;
    private List<String> recipientDepartments;

    private LocalDateTime sentAt;
    
    private boolean sentToAll;
    private List<EmployeeInfoDTO> recipients; // Detailed recipient information
    private int totalRecipients; // Total number of recipients
} 