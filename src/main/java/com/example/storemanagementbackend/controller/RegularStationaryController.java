package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.RegularStationary;
import com.example.storemanagementbackend.service.RegularStationaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/store/stationary/regular")
@CrossOrigin(origins = "*")
public class RegularStationaryController {
    @Autowired
    private RegularStationaryService service;

    @GetMapping
    public List<RegularStationary> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegularStationary> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RegularStationary create(@RequestBody RegularStationary stationary) {
        return service.save(stationary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegularStationary> update(@PathVariable Long id, @RequestBody RegularStationary stationary) {
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