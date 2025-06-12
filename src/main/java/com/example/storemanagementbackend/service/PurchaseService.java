package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.Purchase;
import java.util.List;

public interface PurchaseService {
    List<Purchase> getAllPurchases();
    Purchase getPurchaseById(Long id);
    Purchase createPurchase(Purchase purchase);
    Purchase updatePurchase(Long id, Purchase purchaseDetails);
    void deletePurchase(Long id);
} 