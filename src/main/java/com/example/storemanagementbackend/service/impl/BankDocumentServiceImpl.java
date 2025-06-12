package com.example.storemanagementbackend.service.impl;

import com.example.storemanagementbackend.model.BankDocument;
import com.example.storemanagementbackend.repository.BankDocumentRepository;
import com.example.storemanagementbackend.service.BankDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankDocumentServiceImpl implements BankDocumentService {

    @Autowired
    private BankDocumentRepository bankDocumentRepository;

    @Override
    public List<BankDocument> getAllBankDocuments() {
        return bankDocumentRepository.findAll();
    }

    @Override
    public BankDocument getBankDocumentById(Long id) {
        Optional<BankDocument> bankDocument = bankDocumentRepository.findById(id);
        return bankDocument.orElse(null);
    }

    @Override
    public BankDocument createBankDocument(BankDocument bankDocument) {
        return bankDocumentRepository.save(bankDocument);
    }

    @Override
    public BankDocument updateBankDocument(Long id, BankDocument bankDocumentDetails) {
        BankDocument bankDocument = bankDocumentRepository.findById(id).orElse(null);
        if (bankDocument != null) {
            bankDocument.setDocumentType(bankDocumentDetails.getDocumentType());
            bankDocument.setBankName(bankDocumentDetails.getBankName());
            bankDocument.setAccountNumber(bankDocumentDetails.getAccountNumber());
            bankDocument.setDate(bankDocumentDetails.getDate());
            bankDocument.setStatus(bankDocumentDetails.getStatus());
            return bankDocumentRepository.save(bankDocument);
        }
        return null;
    }

    @Override
    public void deleteBankDocument(Long id) {
        bankDocumentRepository.deleteById(id);
    }
} 