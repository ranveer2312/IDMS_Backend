package com.example.storemanagementbackend.service.impl;

import com.example.storemanagementbackend.model.CaDocument;
import com.example.storemanagementbackend.repository.CaDocumentRepository;
import com.example.storemanagementbackend.service.CaDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaDocumentServiceImpl implements CaDocumentService {

    @Autowired
    private CaDocumentRepository caDocumentRepository;

    @Override
    public List<CaDocument> getAllCaDocuments() {
        return caDocumentRepository.findAll();
    }

    @Override
    public CaDocument getCaDocumentById(Long id) {
        Optional<CaDocument> caDocument = caDocumentRepository.findById(id);
        return caDocument.orElse(null);
    }

    @Override
    public CaDocument createCaDocument(CaDocument caDocument) {
        return caDocumentRepository.save(caDocument);
    }

    @Override
    public CaDocument updateCaDocument(Long id, CaDocument caDocumentDetails) {
        CaDocument caDocument = caDocumentRepository.findById(id).orElse(null);
        if (caDocument != null) {
            caDocument.setDocumentNumber(caDocumentDetails.getDocumentNumber());
            caDocument.setClient(caDocumentDetails.getClient());
            caDocument.setAmount(caDocumentDetails.getAmount());
            caDocument.setDate(caDocumentDetails.getDate());
            caDocument.setDescription(caDocumentDetails.getDescription());
            caDocument.setStatus(caDocumentDetails.getStatus());
            return caDocumentRepository.save(caDocument);
        }
        return null;
    }

    @Override
    public void deleteCaDocument(Long id) {
        caDocumentRepository.deleteById(id);
    }
} 