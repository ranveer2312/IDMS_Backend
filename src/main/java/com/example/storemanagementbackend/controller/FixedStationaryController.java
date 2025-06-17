package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.FixedStationary;
import com.example.storemanagementbackend.service.FixedStationaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/store/stationary/fixed")
@CrossOrigin(origins = "*")
public class FixedStationaryController {
    @Autowired
    private FixedStationaryService service;

    @GetMapping
    public List<FixedStationary> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FixedStationary> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FixedStationary create(@RequestBody FixedStationary stationary) {
        return service.save(stationary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FixedStationary> update(@PathVariable Long id, @RequestBody FixedStationary stationary) {
        return service.findById(id)
                .map(existingStationary -> {
                    return ResponseEntity.ok(service.save(stationary));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(stationary -> {
                    service.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 