package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.Billing;
import com.example.storemanagementbackend.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billings")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping
    public ResponseEntity<List<Billing>> getAllBillings() {
        List<Billing> billings = billingService.getAllBillings();
        return new ResponseEntity<>(billings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Billing> getBillingById(@PathVariable Long id) {
        Billing billing = billingService.getBillingById(id);
        if (billing != null) {
            return new ResponseEntity<>(billing, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Billing> createBilling(@RequestBody Billing billing) {
        Billing createdBilling = billingService.createBilling(billing);
        return new ResponseEntity<>(createdBilling, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Billing> updateBilling(@PathVariable Long id, @RequestBody Billing billingDetails) {
        Billing updatedBilling = billingService.updateBilling(id, billingDetails);
        if (updatedBilling != null) {
            return new ResponseEntity<>(updatedBilling, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBilling(@PathVariable Long id) {
        billingService.deleteBilling(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 