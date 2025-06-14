package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.dto.RentDTO;
import com.example.storemanagementbackend.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rent")
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping
    public ResponseEntity<RentDTO> createRent(@RequestBody RentDTO rentDTO) {
        return ResponseEntity.ok(rentService.createRent(rentDTO));
    }

    @GetMapping
    public ResponseEntity<List<RentDTO>> getAllRents() {
        return ResponseEntity.ok(rentService.getAllRents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentDTO> updateRent(@PathVariable Long id, @RequestBody RentDTO rentDTO) {
        return ResponseEntity.ok(rentService.updateRent(id, rentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        rentService.deleteRent(id);
        return ResponseEntity.ok().build();
    }
} 