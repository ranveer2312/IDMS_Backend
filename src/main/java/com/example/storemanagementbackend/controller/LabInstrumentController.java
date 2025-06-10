package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.LabInstrument;
import com.example.storemanagementbackend.service.LabInstrumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/store/lab/instruments")
@CrossOrigin(origins = "*")
public class LabInstrumentController {
    private final LabInstrumentService service;

    public LabInstrumentController(LabInstrumentService service) {
        this.service = service;
    }

    @GetMapping
    public List<LabInstrument> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabInstrument> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LabInstrument create(@RequestBody LabInstrument instrument) {
        return service.save(instrument);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabInstrument> update(@PathVariable Long id, @RequestBody LabInstrument instrument) {
        return service.findById(id)
                .map(existingInstrument -> {
                    instrument.setId(id);
                    return ResponseEntity.ok(service.save(instrument));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(instrument -> {
                    service.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 