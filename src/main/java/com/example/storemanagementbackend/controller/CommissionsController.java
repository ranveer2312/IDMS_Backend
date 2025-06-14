package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.dto.CommissionsDTO;
import com.example.storemanagementbackend.service.CommissionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commissions")
public class CommissionsController {
    private final CommissionsService commissionsService;

    public CommissionsController(CommissionsService commissionsService) {
        this.commissionsService = commissionsService;
    }

    @PostMapping
    public ResponseEntity<CommissionsDTO> createCommission(@RequestBody CommissionsDTO commissionsDTO) {
        return ResponseEntity.ok(commissionsService.createCommission(commissionsDTO));
    }

    @GetMapping
    public ResponseEntity<List<CommissionsDTO>> getAllCommissions() {
        return ResponseEntity.ok(commissionsService.getAllCommissions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommissionsDTO> updateCommission(@PathVariable Long id, @RequestBody CommissionsDTO commissionsDTO) {
        return ResponseEntity.ok(commissionsService.updateCommission(id, commissionsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommission(@PathVariable Long id) {
        commissionsService.deleteCommission(id);
        return ResponseEntity.ok().build();
    }
} 