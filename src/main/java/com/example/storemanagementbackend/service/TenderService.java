package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.Tender;
import java.util.List;

public interface TenderService {
    List<Tender> getAllTenders();
    Tender getTenderById(Long id);
    Tender createTender(Tender tender);
    Tender updateTender(Long id, Tender tenderDetails);
    void deleteTender(Long id);
} 