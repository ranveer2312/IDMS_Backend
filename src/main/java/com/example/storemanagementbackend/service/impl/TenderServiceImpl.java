package com.example.storemanagementbackend.service.impl;

import com.example.storemanagementbackend.model.Tender;
import com.example.storemanagementbackend.repository.TenderRepository;
import com.example.storemanagementbackend.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenderServiceImpl implements TenderService {

    @Autowired
    private TenderRepository tenderRepository;

    @Override
    public List<Tender> getAllTenders() {
        return tenderRepository.findAll();
    }

    @Override
    public Tender getTenderById(Long id) {
        Optional<Tender> tender = tenderRepository.findById(id);
        return tender.orElse(null);
    }

    @Override
    public Tender createTender(Tender tender) {
        return tenderRepository.save(tender);
    }

    @Override
    public Tender updateTender(Long id, Tender tenderDetails) {
        Tender tender = tenderRepository.findById(id).orElse(null);
        if (tender != null) {
            tender.setTenderNumber(tenderDetails.getTenderNumber());
            tender.setTitle(tenderDetails.getTitle());
            tender.setOrganization(tenderDetails.getOrganization());
            tender.setSubmissionDate(tenderDetails.getSubmissionDate());
            tender.setOpeningDate(tenderDetails.getOpeningDate());
            tender.setEstimatedValue(tenderDetails.getEstimatedValue());
            tender.setCategory(tenderDetails.getCategory());
            tender.setStatus(tenderDetails.getStatus());
            return tenderRepository.save(tender);
        }
        return null;
    }

    @Override
    public void deleteTender(Long id) {
        tenderRepository.deleteById(id);
    }
} 