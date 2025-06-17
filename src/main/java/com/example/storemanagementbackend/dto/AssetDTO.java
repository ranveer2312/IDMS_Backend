package com.example.storemanagementbackend.dto;
public class AssetDTO {
    private Long id;
    private String assetName;
    private String category;
    private String serialNumber;
    private String status;
    private String condition;
    private String assignedTo;
 
    public AssetDTO() {
    }
 
    public AssetDTO(Long id, String assetName, String category, String serialNumber,
                    String status, String condition, String assignedTo) {
        this.id = id;
        this.assetName = assetName;
        this.category = category;
        this.serialNumber = serialNumber;
        this.status = status;
        this.condition = condition;
        this.assignedTo = assignedTo;
    }
 
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }
 
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
 
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
 
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
 
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
 
    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}
 
 
 