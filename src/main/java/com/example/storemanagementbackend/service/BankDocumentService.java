package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.BankDocument;
import java.util.List;

public interface BankDocumentService {
    List<BankDocument> getAllBankDocuments();
    BankDocument getBankDocumentById(Long id);
    BankDocument createBankDocument(BankDocument bankDocument);
    BankDocument updateBankDocument(Long id, BankDocument bankDocumentDetails);
    void deleteBankDocument(Long id);
} 