package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.LabMaterial;
import com.example.storemanagementbackend.service.LabMaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/store/lab/materials")
@CrossOrigin(origins = "https://idmsproject.vercel.app")
public class LabMaterialController {
    private final LabMaterialService service;

    public LabMaterialController(LabMaterialService service) {
        this.service = service;
    }

    @GetMapping
    public List<LabMaterial> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabMaterial> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LabMaterial create(@RequestBody LabMaterial material) {
        return service.save(material);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabMaterial> update(@PathVariable Long id, @RequestBody LabMaterial material) {
        return service.findById(id)
                .map(existingMaterial -> {
                    return ResponseEntity.ok(service.save(material));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(material -> {
                    service.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 