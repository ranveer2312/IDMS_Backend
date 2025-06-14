package com.example.storemanagementbackend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ElectricBillsDTO {
    private Long id;
    private BigDecimal amount;
    private LocalDate date;
    private String description;

    // Getters
    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }
} 