package com.example.storemanagementbackend.service.impl;

import com.example.storemanagementbackend.model.CompanyRegistration;
import com.example.storemanagementbackend.repository.CompanyRegistrationRepository;
import com.example.storemanagementbackend.service.CompanyRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyRegistrationServiceImpl implements CompanyRegistrationService {

    @Autowired
    private CompanyRegistrationRepository companyRegistrationRepository;

    @Override
    public List<CompanyRegistration> getAllCompanyRegistrations() {
        return companyRegistrationRepository.findAll();
    }

    @Override
    public CompanyRegistration getCompanyRegistrationById(Long id) {
        Optional<CompanyRegistration> companyRegistration = companyRegistrationRepository.findById(id);
        return companyRegistration.orElse(null);
    }

    @Override
    public CompanyRegistration createCompanyRegistration(CompanyRegistration companyRegistration) {
        return companyRegistrationRepository.save(companyRegistration);
    }

    @Override
    public CompanyRegistration updateCompanyRegistration(Long id, CompanyRegistration companyRegistrationDetails) {
        CompanyRegistration companyRegistration = companyRegistrationRepository.findById(id).orElse(null);
        if (companyRegistration != null) {
            companyRegistration.setCompanyName(companyRegistrationDetails.getCompanyName());
            companyRegistration.setRegistrationNumber(companyRegistrationDetails.getRegistrationNumber());
            companyRegistration.setType(companyRegistrationDetails.getType());
            companyRegistration.setDate(companyRegistrationDetails.getDate());
            companyRegistration.setStatus(companyRegistrationDetails.getStatus());
            return companyRegistrationRepository.save(companyRegistration);
        }
        return null;
    }

    @Override
    public void deleteCompanyRegistration(Long id) {
        companyRegistrationRepository.deleteById(id);
    }
} 