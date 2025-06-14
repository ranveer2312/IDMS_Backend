package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.dto.TravelDTO;
import com.example.storemanagementbackend.service.TravelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel")
public class TravelController {
    private final TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    @PostMapping
    public ResponseEntity<TravelDTO> createTravel(@RequestBody TravelDTO travelDTO) {
        return ResponseEntity.ok(travelService.createTravel(travelDTO));
    }

    @GetMapping
    public ResponseEntity<List<TravelDTO>> getAllTravels() {
        return ResponseEntity.ok(travelService.getAllTravels());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelDTO> updateTravel(@PathVariable Long id, @RequestBody TravelDTO travelDTO) {
        return ResponseEntity.ok(travelService.updateTravel(id, travelDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTravel(@PathVariable Long id) {
        travelService.deleteTravel(id);
        return ResponseEntity.ok().build();
    }
} 