package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.LogisticsDocument;
import com.example.storemanagementbackend.service.LogisticsDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logisticsdocuments")
public class LogisticsDocumentController {

    @Autowired
    private LogisticsDocumentService logisticsDocumentService;

    @GetMapping
    public ResponseEntity<List<LogisticsDocument>> getAllLogisticsDocuments() {
        List<LogisticsDocument> logisticsDocuments = logisticsDocumentService.getAllLogisticsDocuments();
        return new ResponseEntity<>(logisticsDocuments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogisticsDocument> getLogisticsDocumentById(@PathVariable Long id) {
        LogisticsDocument logisticsDocument = logisticsDocumentService.getLogisticsDocumentById(id);
        if (logisticsDocument != null) {
            return new ResponseEntity<>(logisticsDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<LogisticsDocument> createLogisticsDocument(@RequestBody LogisticsDocument logisticsDocument) {
        LogisticsDocument createdLogisticsDocument = logisticsDocumentService.createLogisticsDocument(logisticsDocument);
        return new ResponseEntity<>(createdLogisticsDocument, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogisticsDocument> updateLogisticsDocument(@PathVariable Long id, @RequestBody LogisticsDocument logisticsDocumentDetails) {
        LogisticsDocument updatedLogisticsDocument = logisticsDocumentService.updateLogisticsDocument(id, logisticsDocumentDetails);
        if (updatedLogisticsDocument != null) {
            return new ResponseEntity<>(updatedLogisticsDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogisticsDocument(@PathVariable Long id) {
        logisticsDocumentService.deleteLogisticsDocument(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 