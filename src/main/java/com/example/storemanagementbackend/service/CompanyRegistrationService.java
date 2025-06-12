package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.CompanyRegistration;
import java.util.List;

public interface CompanyRegistrationService {
    List<CompanyRegistration> getAllCompanyRegistrations();
    CompanyRegistration getCompanyRegistrationById(Long id);
    CompanyRegistration createCompanyRegistration(CompanyRegistration companyRegistration);
    CompanyRegistration updateCompanyRegistration(Long id, CompanyRegistration companyRegistrationDetails);
    void deleteCompanyRegistration(Long id);
} 