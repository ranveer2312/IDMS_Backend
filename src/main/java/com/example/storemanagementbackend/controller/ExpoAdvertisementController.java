package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.dto.ExpoAdvertisementDTO;
import com.example.storemanagementbackend.service.ExpoAdvertisementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expo-advertisements")
public class ExpoAdvertisementController {
    private final ExpoAdvertisementService expoAdvertisementService;

    public ExpoAdvertisementController(ExpoAdvertisementService expoAdvertisementService) {
        this.expoAdvertisementService = expoAdvertisementService;
    }

    @PostMapping
    public ResponseEntity<ExpoAdvertisementDTO> createExpoAdvertisement(@RequestBody ExpoAdvertisementDTO expoAdvertisementDTO) {
        return ResponseEntity.ok(expoAdvertisementService.createExpoAdvertisement(expoAdvertisementDTO));
    }

    @GetMapping
    public ResponseEntity<List<ExpoAdvertisementDTO>> getAllExpoAdvertisements() {
        return ResponseEntity.ok(expoAdvertisementService.getAllExpoAdvertisements());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpoAdvertisementDTO> updateExpoAdvertisement(@PathVariable Long id, @RequestBody ExpoAdvertisementDTO expoAdvertisementDTO) {
        return ResponseEntity.ok(expoAdvertisementService.updateExpoAdvertisement(id, expoAdvertisementDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpoAdvertisement(@PathVariable Long id) {
        expoAdvertisementService.deleteExpoAdvertisement(id);
        return ResponseEntity.ok().build();
    }
} 