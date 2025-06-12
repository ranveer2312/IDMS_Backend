package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.CaDocument;
import java.util.List;

public interface CaDocumentService {
    List<CaDocument> getAllCaDocuments();
    CaDocument getCaDocumentById(Long id);
    CaDocument createCaDocument(CaDocument caDocument);
    CaDocument updateCaDocument(Long id, CaDocument caDocumentDetails);
    void deleteCaDocument(Long id);
} 