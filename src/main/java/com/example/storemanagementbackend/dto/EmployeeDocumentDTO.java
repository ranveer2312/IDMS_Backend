package com.example.storemanagementbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDocumentDTO {
    private Long id;
    private String employeeId;
    private String documentType;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
 
 