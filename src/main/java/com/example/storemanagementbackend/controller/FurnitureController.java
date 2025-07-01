package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.Furniture;
import com.example.storemanagementbackend.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/store/assets/furniture")
@CrossOrigin(origins = "https://idmsproject.vercel.app")
public class FurnitureController {
    @Autowired
    private FurnitureService service;

    @GetMapping
    public List<Furniture> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Furniture> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Furniture create(@RequestBody Furniture furniture) {
        return service.save(furniture);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Furniture> update(@PathVariable Long id, @RequestBody Furniture furniture) {
        return service.findById(id)
                .map(existingFurniture -> {
                    furniture.setId(id);
                    return ResponseEntity.ok(service.save(furniture));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(furniture -> {
                    service.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 