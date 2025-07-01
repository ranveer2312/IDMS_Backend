package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.System;
import com.example.storemanagementbackend.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/store/assets/systems")
@CrossOrigin(origins = "https://idmsproject.vercel.app")
public class SystemController {
    @Autowired
    private SystemService service;

    @GetMapping
    public List<System> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<System> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public System create(@RequestBody System system) {
        return service.save(system);
    }

    @PutMapping("/{id}")
    public ResponseEntity<System> update(@PathVariable Long id, @RequestBody System system) {
        return service.findById(id)
                .map(existingSystem -> {
                    system.setId(id);
                    return ResponseEntity.ok(service.save(system));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(system -> {
                    service.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 