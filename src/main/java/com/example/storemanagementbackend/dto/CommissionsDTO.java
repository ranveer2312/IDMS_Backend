package com.example.storemanagementbackend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CommissionsDTO {
    private Long id;
    private BigDecimal amount;
    private LocalDate date;
    private String description;
    private String recipient;

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

    public String getRecipient() {
        return recipient;
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

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
} 