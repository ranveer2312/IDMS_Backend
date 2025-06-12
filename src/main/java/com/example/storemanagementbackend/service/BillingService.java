package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.Billing;
import java.util.List;

public interface BillingService {
    List<Billing> getAllBillings();
    Billing getBillingById(Long id);
    Billing createBilling(Billing billing);
    Billing updateBilling(Long id, Billing billingDetails);
    void deleteBilling(Long id);
} 