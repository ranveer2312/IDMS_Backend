package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.model.CompanyRegistration;
import com.example.storemanagementbackend.service.CompanyRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companyregistrations")
public class CompanyRegistrationController {

    @Autowired
    private CompanyRegistrationService companyRegistrationService;

    @GetMapping
    public ResponseEntity<List<CompanyRegistration>> getAllCompanyRegistrations() {
        List<CompanyRegistration> companyRegistrations = companyRegistrationService.getAllCompanyRegistrations();
        return new ResponseEntity<>(companyRegistrations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyRegistration> getCompanyRegistrationById(@PathVariable Long id) {
        CompanyRegistration companyRegistration = companyRegistrationService.getCompanyRegistrationById(id);
        if (companyRegistration != null) {
            return new ResponseEntity<>(companyRegistration, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CompanyRegistration> createCompanyRegistration(@RequestBody CompanyRegistration companyRegistration) {
        CompanyRegistration createdCompanyRegistration = companyRegistrationService.createCompanyRegistration(companyRegistration);
        return new ResponseEntity<>(createdCompanyRegistration, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyRegistration> updateCompanyRegistration(@PathVariable Long id, @RequestBody CompanyRegistration companyRegistrationDetails) {
        CompanyRegistration updatedCompanyRegistration = companyRegistrationService.updateCompanyRegistration(id, companyRegistrationDetails);
        if (updatedCompanyRegistration != null) {
            return new ResponseEntity<>(updatedCompanyRegistration, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompanyRegistration(@PathVariable Long id) {
        companyRegistrationService.deleteCompanyRegistration(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 