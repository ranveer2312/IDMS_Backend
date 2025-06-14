package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.dto.IncentivesDTO;
import com.example.storemanagementbackend.service.IncentivesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incentives")
public class IncentivesController {
    private final IncentivesService incentivesService;

    public IncentivesController(IncentivesService incentivesService) {
        this.incentivesService = incentivesService;
    }

    @PostMapping
    public ResponseEntity<IncentivesDTO> createIncentive(@RequestBody IncentivesDTO incentivesDTO) {
        return ResponseEntity.ok(incentivesService.createIncentive(incentivesDTO));
    }

    @GetMapping
    public ResponseEntity<List<IncentivesDTO>> getAllIncentives() {
        return ResponseEntity.ok(incentivesService.getAllIncentives());
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncentivesDTO> updateIncentive(@PathVariable Long id, @RequestBody IncentivesDTO incentivesDTO) {
        return ResponseEntity.ok(incentivesService.updateIncentive(id, incentivesDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncentive(@PathVariable Long id) {
        incentivesService.deleteIncentive(id);
        return ResponseEntity.ok().build();
    }
} 