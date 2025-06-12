package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.CaDocument;
import com.example.storemanagementbackend.service.CaDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cadocuments")
public class CaDocumentController {

    @Autowired
    private CaDocumentService caDocumentService;

    @GetMapping
    public ResponseEntity<List<CaDocument>> getAllCaDocuments() {
        List<CaDocument> caDocuments = caDocumentService.getAllCaDocuments();
        return new ResponseEntity<>(caDocuments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaDocument> getCaDocumentById(@PathVariable Long id) {
        CaDocument caDocument = caDocumentService.getCaDocumentById(id);
        if (caDocument != null) {
            return new ResponseEntity<>(caDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CaDocument> createCaDocument(@RequestBody CaDocument caDocument) {
        CaDocument createdCaDocument = caDocumentService.createCaDocument(caDocument);
        return new ResponseEntity<>(createdCaDocument, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaDocument> updateCaDocument(@PathVariable Long id, @RequestBody CaDocument caDocumentDetails) {
        CaDocument updatedCaDocument = caDocumentService.updateCaDocument(id, caDocumentDetails);
        if (updatedCaDocument != null) {
            return new ResponseEntity<>(updatedCaDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaDocument(@PathVariable Long id) {
        caDocumentService.deleteCaDocument(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 