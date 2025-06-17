package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.LabInventoryTransaction;
import com.example.storemanagementbackend.service.LabInventoryTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/store/lab/inventory")
@CrossOrigin(origins = "*")
public class LabInventoryTransactionController {
    @Autowired
    private LabInventoryTransactionService service;

    @GetMapping
    public List<LabInventoryTransaction> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabInventoryTransaction> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LabInventoryTransaction create(@RequestBody LabInventoryTransaction transaction) {
        return service.save(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabInventoryTransaction> update(@PathVariable Long id, @RequestBody LabInventoryTransaction transaction) {
        return service.findById(id)
                .map(existingTransaction -> {
                    return ResponseEntity.ok(service.save(transaction));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(transaction -> {
                    service.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 