package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.StationaryInventoryTransaction;
import com.example.storemanagementbackend.service.StationaryInventoryTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/store/stationary/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class StationaryInventoryTransactionController {
    private final StationaryInventoryTransactionService service;

    public StationaryInventoryTransactionController(StationaryInventoryTransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<StationaryInventoryTransaction> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationaryInventoryTransaction> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StationaryInventoryTransaction create(@RequestBody StationaryInventoryTransaction transaction) {
        return service.save(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StationaryInventoryTransaction> update(@PathVariable Long id, @RequestBody StationaryInventoryTransaction transaction) {
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