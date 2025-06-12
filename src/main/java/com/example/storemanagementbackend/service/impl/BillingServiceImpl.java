package com.example.storemanagementbackend.service.impl;

import com.example.storemanagementbackend.model.Billing;
import com.example.storemanagementbackend.repository.BillingRepository;
import com.example.storemanagementbackend.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Override
    public List<Billing> getAllBillings() {
        return billingRepository.findAll();
    }

    @Override
    public Billing getBillingById(Long id) {
        Optional<Billing> billing = billingRepository.findById(id);
        return billing.orElse(null);
    }

    @Override
    public Billing createBilling(Billing billing) {
        return billingRepository.save(billing);
    }

    @Override
    public Billing updateBilling(Long id, Billing billingDetails) {
        Billing billing = billingRepository.findById(id).orElse(null);
        if (billing != null) {
            billing.setInvoiceNumber(billingDetails.getInvoiceNumber());
            billing.setClient(billingDetails.getClient());
            billing.setAmount(billingDetails.getAmount());
            billing.setDueDate(billingDetails.getDueDate());
            billing.setType(billingDetails.getType());
            billing.setStatus(billingDetails.getStatus());
            return billingRepository.save(billing);
        }
        return null;
    }

    @Override
    public void deleteBilling(Long id) {
        billingRepository.deleteById(id);
    }
} 