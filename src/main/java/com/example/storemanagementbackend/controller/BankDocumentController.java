package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.BankDocument;
import com.example.storemanagementbackend.service.BankDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bankdocuments")
public class BankDocumentController {

    @Autowired
    private BankDocumentService bankDocumentService;

    @GetMapping
    public ResponseEntity<List<BankDocument>> getAllBankDocuments() {
        List<BankDocument> bankDocuments = bankDocumentService.getAllBankDocuments();
        return new ResponseEntity<>(bankDocuments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankDocument> getBankDocumentById(@PathVariable Long id) {
        BankDocument bankDocument = bankDocumentService.getBankDocumentById(id);
        if (bankDocument != null) {
            return new ResponseEntity<>(bankDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BankDocument> createBankDocument(@RequestBody BankDocument bankDocument) {
        BankDocument createdBankDocument = bankDocumentService.createBankDocument(bankDocument);
        return new ResponseEntity<>(createdBankDocument, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankDocument> updateBankDocument(@PathVariable Long id, @RequestBody BankDocument bankDocumentDetails) {
        BankDocument updatedBankDocument = bankDocumentService.updateBankDocument(id, bankDocumentDetails);
        if (updatedBankDocument != null) {
            return new ResponseEntity<>(updatedBankDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankDocument(@PathVariable Long id) {
        bankDocumentService.deleteBankDocument(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 