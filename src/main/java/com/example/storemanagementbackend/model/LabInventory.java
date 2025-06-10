package com.example.storemanagementbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lab_inventory")
public class LabInventory extends Item {
    private String name;
    private String category;
    private String type; // Instrument, Component, Material
    private String referenceId; // ID of the referenced item
    private Integer currentStock;
    private Integer minimumStock;
    private Integer reorderPoint;
    private String unit;
    private Double unitPrice;
    private String location;
    private String supplier;
    private LocalDate lastRestocked;
    private LocalDate nextRestockDate;
    private String status; // Available, Low Stock, Out of Stock
    private String notes;
} 