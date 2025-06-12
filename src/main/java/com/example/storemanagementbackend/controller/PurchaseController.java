package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.Purchase;
import com.example.storemanagementbackend.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable Long id) {
        Purchase purchase = purchaseService.getPurchaseById(id);
        if (purchase != null) {
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
        Purchase createdPurchase = purchaseService.createPurchase(purchase);
        return new ResponseEntity<>(createdPurchase, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable Long id, @RequestBody Purchase purchaseDetails) {
        Purchase updatedPurchase = purchaseService.updatePurchase(id, purchaseDetails);
        if (updatedPurchase != null) {
            return new ResponseEntity<>(updatedPurchase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
        purchaseService.deletePurchase(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 