package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.dto.SalariesDTO;
import com.example.storemanagementbackend.service.SalariesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salaries")
public class SalariesController {
    private final SalariesService salariesService;

    public SalariesController(SalariesService salariesService) {
        this.salariesService = salariesService;
    }

    @PostMapping
    public ResponseEntity<SalariesDTO> createSalary(@RequestBody SalariesDTO salariesDTO) {
        return ResponseEntity.ok(salariesService.createSalary(salariesDTO));
    }

    @GetMapping
    public ResponseEntity<List<SalariesDTO>> getAllSalaries() {
        return ResponseEntity.ok(salariesService.getAllSalaries());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalariesDTO> updateSalary(@PathVariable Long id, @RequestBody SalariesDTO salariesDTO) {
        return ResponseEntity.ok(salariesService.updateSalary(id, salariesDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) {
        salariesService.deleteSalary(id);
        return ResponseEntity.ok().build();
    }
} 