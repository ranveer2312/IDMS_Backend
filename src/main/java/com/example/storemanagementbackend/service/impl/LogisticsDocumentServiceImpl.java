package com.example.storemanagementbackend.service.impl;

import com.example.storemanagementbackend.model.LogisticsDocument;
import com.example.storemanagementbackend.repository.LogisticsDocumentRepository;
import com.example.storemanagementbackend.service.LogisticsDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogisticsDocumentServiceImpl implements LogisticsDocumentService {

    @Autowired
    private LogisticsDocumentRepository logisticsDocumentRepository;

    @Override
    public List<LogisticsDocument> getAllLogisticsDocuments() {
        return logisticsDocumentRepository.findAll();
    }

    @Override
    public LogisticsDocument getLogisticsDocumentById(Long id) {
        Optional<LogisticsDocument> logisticsDocument = logisticsDocumentRepository.findById(id);
        return logisticsDocument.orElse(null);
    }

    @Override
    public LogisticsDocument createLogisticsDocument(LogisticsDocument logisticsDocument) {
        return logisticsDocumentRepository.save(logisticsDocument);
    }

    @Override
    public LogisticsDocument updateLogisticsDocument(Long id, LogisticsDocument logisticsDocumentDetails) {
        LogisticsDocument logisticsDocument = logisticsDocumentRepository.findById(id).orElse(null);
        if (logisticsDocument != null) {
            logisticsDocument.setDocumentType(logisticsDocumentDetails.getDocumentType());
            logisticsDocument.setReference(logisticsDocumentDetails.getReference());
            logisticsDocument.setDate(logisticsDocumentDetails.getDate());
            logisticsDocument.setStatus(logisticsDocumentDetails.getStatus());
            logisticsDocument.setOrigin(logisticsDocumentDetails.getOrigin());
            logisticsDocument.setDestination(logisticsDocumentDetails.getDestination());
            logisticsDocument.setCarrier(logisticsDocumentDetails.getCarrier());
            return logisticsDocumentRepository.save(logisticsDocument);
        }
        return null;
    }

    @Override
    public void deleteLogisticsDocument(Long id) {
        logisticsDocumentRepository.deleteById(id);
    }
} 