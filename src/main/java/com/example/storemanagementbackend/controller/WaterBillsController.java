package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.dto.WaterBillsDTO;
import com.example.storemanagementbackend.service.WaterBillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/water-bills")
@RequiredArgsConstructor
public class WaterBillsController {
    private final WaterBillsService waterBillsService;

    @PostMapping
    public ResponseEntity<WaterBillsDTO> createWaterBillExpense(@RequestBody WaterBillsDTO request) {
        return ResponseEntity.ok(waterBillsService.createWaterBill(request));
    }

    @GetMapping
    public ResponseEntity<List<WaterBillsDTO>> getWaterBillExpenses() {
        return ResponseEntity.ok(waterBillsService.getAllWaterBills());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaterBillsDTO> updateWaterBillExpense(@PathVariable Long id, @RequestBody WaterBillsDTO request) {
        return ResponseEntity.ok(waterBillsService.updateWaterBill(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWaterBillExpense(@PathVariable Long id) {
        waterBillsService.deleteWaterBill(id);
        return ResponseEntity.ok().build();
    }
} 