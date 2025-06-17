    package com.example.storemanagementbackend.model;
    import jakarta.persistence.*;
 
    @Entity
    @Table(name = "assets")
    public class Asset {
 
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
 
        @Column(name = "asset_name")
        private String assetName;
 
        @Column(name = "category")
        private String category;
 
        public Asset(Long id, String assetName, String category, String assetcondition, String status, String serialNumber, String assignedTo) {
            this.id = id;
            this.assetName = assetName;
            this.category = category;
            this.assetcondition = assetcondition;
            this.status = status;
            this.serialNumber = serialNumber;
            this.assignedTo = assignedTo;
        }
 
        public Asset() {
        }
 
        @Column(name = "serial_number")
        private String serialNumber;
 
        @Column(name = "status")
        private String status;
 
        @Column(name = "assetcondition")
        private String assetcondition;
 
        @Column(name = "assigned_to")
        private String assignedTo;
 
        public Long getId() {
            return id;
        }
 
        public void setId(Long id) {
            this.id = id;
        }
 
        public String getAssetName() {
            return assetName;
        }
 
        public void setAssetName(String assetName) {
            this.assetName = assetName;
        }
 
        public String getCategory() {
            return category;
        }
 
        public void setCategory(String category) {
            this.category = category;
        }
 
        public String getSerialNumber() {
            return serialNumber;
        }
 
        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }
 
        public String getStatus() {
            return status;
        }
 
        public void setStatus(String status) {
            this.status = status;
        }
 
        public String getAssetcondition() {
            return assetcondition;
        }
 
        public void setCondition(String assetcondition) {
            this.assetcondition = assetcondition;
        }
 
        public String getAssignedTo() {
            return assignedTo;
        }
 
        public void setAssignedTo(String assignedTo) {
            this.assignedTo = assignedTo;
        }
    }
 
 