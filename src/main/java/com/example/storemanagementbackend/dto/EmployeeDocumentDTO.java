package com.example.storemanagementbackend.dto;
public class EmployeeDocumentDTO {
    private String employeeId;
    private String documentType;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;
 
    public EmployeeDocumentDTO() {
    }
 
    public EmployeeDocumentDTO(String employeeId, String documentType, String fileName, String fileDownloadUri, String fileType, Long size) {
        this.employeeId = employeeId;
        this.documentType = documentType;
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
 
    public String getEmployeeId() {
        return employeeId;
    }
 
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
 
    public String getDocumentType() {
        return documentType;
    }
 
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
 
    public String getFileName() {
        return fileName;
    }
 
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
 
    public String getFileDownloadUri() {
        return fileDownloadUri;
    }
 
    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }
 
    public String getFileType() {
        return fileType;
    }
 
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
 
    public Long getSize() {
        return size;
    }
 
    public void setSize(Long size) {
        this.size = size;
    }
}
 
 