package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.Printer;
import com.example.storemanagementbackend.service.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/store/assets/printers")
@CrossOrigin(origins = "*")
public class PrinterController {
    @Autowired
    private PrinterService service;

    @GetMapping
    public List<Printer> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Printer> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Printer create(@RequestBody Printer printer) {
        return service.save(printer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Printer> update(@PathVariable Long id, @RequestBody Printer printer) {
        return service.findById(id)
                .map(existingPrinter -> {
                    printer.setId(id);
                    return ResponseEntity.ok(service.save(printer));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(printer -> {
                    service.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 