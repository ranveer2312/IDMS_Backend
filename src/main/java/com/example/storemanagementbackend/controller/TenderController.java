package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.Tender;
import com.example.storemanagementbackend.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenders")
public class TenderController {

    @Autowired
    private TenderService tenderService;

    @GetMapping
    public ResponseEntity<List<Tender>> getAllTenders() {
        List<Tender> tenders = tenderService.getAllTenders();
        return new ResponseEntity<>(tenders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tender> getTenderById(@PathVariable Long id) {
        Tender tender = tenderService.getTenderById(id);
        if (tender != null) {
            return new ResponseEntity<>(tender, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Tender> createTender(@RequestBody Tender tender) {
        Tender createdTender = tenderService.createTender(tender);
        return new ResponseEntity<>(createdTender, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tender> updateTender(@PathVariable Long id, @RequestBody Tender tenderDetails) {
        Tender updatedTender = tenderService.updateTender(id, tenderDetails);
        if (updatedTender != null) {
            return new ResponseEntity<>(updatedTender, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTender(@PathVariable Long id) {
        tenderService.deleteTender(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 