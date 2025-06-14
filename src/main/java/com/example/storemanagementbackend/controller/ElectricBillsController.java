package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.dto.ElectricBillsDTO;
import com.example.storemanagementbackend.service.ElectricBillsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/electric-bills")
public class ElectricBillsController {
    private final ElectricBillsService electricBillsService;

    public ElectricBillsController(ElectricBillsService electricBillsService) {
        this.electricBillsService = electricBillsService;
    }

    @PostMapping
    public ResponseEntity<ElectricBillsDTO> createElectricBill(@RequestBody ElectricBillsDTO electricBillsDTO) {
        return ResponseEntity.ok(electricBillsService.createElectricBill(electricBillsDTO));
    }

    @GetMapping
    public ResponseEntity<List<ElectricBillsDTO>> getAllElectricBills() {
        return ResponseEntity.ok(electricBillsService.getAllElectricBills());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElectricBillsDTO> updateElectricBill(@PathVariable Long id, @RequestBody ElectricBillsDTO electricBillsDTO) {
        return ResponseEntity.ok(electricBillsService.updateElectricBill(id, electricBillsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElectricBill(@PathVariable Long id) {
        electricBillsService.deleteElectricBill(id);
        return ResponseEntity.ok().build();
    }
} 