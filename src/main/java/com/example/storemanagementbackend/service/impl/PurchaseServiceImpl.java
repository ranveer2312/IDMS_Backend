package com.example.storemanagementbackend.service.impl;

import com.example.storemanagementbackend.model.Purchase;
import com.example.storemanagementbackend.repository.PurchaseRepository;
import com.example.storemanagementbackend.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase getPurchaseById(Long id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        return purchase.orElse(null);
    }

    @Override
    public Purchase createPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase updatePurchase(Long id, Purchase purchaseDetails) {
        Purchase purchase = purchaseRepository.findById(id).orElse(null);
        if (purchase != null) {
            purchase.setVendor(purchaseDetails.getVendor());
            purchase.setAmount(purchaseDetails.getAmount());
            purchase.setDate(purchaseDetails.getDate());
            purchase.setStatus(purchaseDetails.getStatus());
            purchase.setPaymentStatus(purchaseDetails.getPaymentStatus());
            purchase.setPaymentMethod(purchaseDetails.getPaymentMethod());
            return purchaseRepository.save(purchase);
        }
        return null;
    }

    @Override
    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }
} 