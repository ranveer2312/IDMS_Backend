package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.dto.InternetBillsDTO;
import com.example.storemanagementbackend.service.InternetBillsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internet-bills")
public class InternetBillsController {
    private final InternetBillsService internetBillsService;

    public InternetBillsController(InternetBillsService internetBillsService) {
        this.internetBillsService = internetBillsService;
    }

    @PostMapping
    public ResponseEntity<InternetBillsDTO> createInternetBill(@RequestBody InternetBillsDTO internetBillsDTO) {
        return ResponseEntity.ok(internetBillsService.createInternetBill(internetBillsDTO));
    }

    @GetMapping
    public ResponseEntity<List<InternetBillsDTO>> getAllInternetBills() {
        return ResponseEntity.ok(internetBillsService.getAllInternetBills());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternetBillsDTO> updateInternetBill(@PathVariable Long id, @RequestBody InternetBillsDTO internetBillsDTO) {
        return ResponseEntity.ok(internetBillsService.updateInternetBill(id, internetBillsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInternetBill(@PathVariable Long id) {
        internetBillsService.deleteInternetBill(id);
        return ResponseEntity.ok().build();
    }
} 