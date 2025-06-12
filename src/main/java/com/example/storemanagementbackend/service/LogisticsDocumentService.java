package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.LogisticsDocument;
import java.util.List;

public interface LogisticsDocumentService {
    List<LogisticsDocument> getAllLogisticsDocuments();
    LogisticsDocument getLogisticsDocumentById(Long id);
    LogisticsDocument createLogisticsDocument(LogisticsDocument logisticsDocument);
    LogisticsDocument updateLogisticsDocument(Long id, LogisticsDocument logisticsDocumentDetails);
    void deleteLogisticsDocument(Long id);
} 