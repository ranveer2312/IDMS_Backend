package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.dto.SIMBillsDTO;
import com.example.storemanagementbackend.service.SIMBillsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sim-bills")
public class SIMBillsController {
    private final SIMBillsService simBillsService;

    public SIMBillsController(SIMBillsService simBillsService) {
        this.simBillsService = simBillsService;
    }

    @PostMapping
    public ResponseEntity<SIMBillsDTO> createSIMBillExpense(@RequestBody SIMBillsDTO simBillsDTO) {
        return ResponseEntity.ok(simBillsService.createSIMBill(simBillsDTO));
    }

    @GetMapping
    public ResponseEntity<List<SIMBillsDTO>> getSIMBillExpenses() {
        return ResponseEntity.ok(simBillsService.getAllSIMBills());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SIMBillsDTO> updateSIMBillExpense(@PathVariable Long id, @RequestBody SIMBillsDTO simBillsDTO) {
        return ResponseEntity.ok(simBillsService.updateSIMBill(id, simBillsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSIMBillExpense(@PathVariable Long id) {
        simBillsService.deleteSIMBill(id);
        return ResponseEntity.ok().build();
    }
} 