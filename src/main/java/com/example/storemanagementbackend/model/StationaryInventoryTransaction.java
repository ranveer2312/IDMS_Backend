package com.example.storemanagementbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "stationary_inventory_transactions")
public class StationaryInventoryTransaction extends Item {
    private String item; // Name of the item
    private String type; // "IN" or "OUT"
    private Integer quantity;
    private String location;
    private LocalDate date;
    private String notes;
} 