package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.LabComponent;
import com.example.storemanagementbackend.service.LabComponentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/store/lab/components")
@CrossOrigin(origins = "*")
public class LabComponentController {
    private final LabComponentService service;

    public LabComponentController(LabComponentService service) {
        this.service = service;
    }

    @GetMapping
    public List<LabComponent> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabComponent> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LabComponent create(@RequestBody LabComponent component) {
        return service.save(component);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabComponent> update(@PathVariable Long id, @RequestBody LabComponent component) {
        return service.findById(id)
                .map(existingComponent -> {
                    component.setId(id);
                    return ResponseEntity.ok(service.save(component));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(component -> {
                    service.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 