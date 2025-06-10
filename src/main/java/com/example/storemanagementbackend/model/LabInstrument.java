package com.example.storemanagementbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lab_instruments")
public class LabInstrument extends Item {
    private String name;
    private String category;
    private Integer quantity;
    private String location;
    private String itemCondition;
    private LocalDate lastUpdated;
} 