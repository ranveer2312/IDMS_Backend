package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.dto.InternetBillsDTO;
import com.example.storemanagementbackend.model.InternetBills;
import com.example.storemanagementbackend.repository.InternetBillsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternetBillsService {
    private final InternetBillsRepository internetBillsRepository;

    public InternetBillsService(InternetBillsRepository internetBillsRepository) {
        this.internetBillsRepository = internetBillsRepository;
    }

    public InternetBillsDTO createInternetBill(InternetBillsDTO dto) {
        InternetBills internetBill = new InternetBills();
        internetBill.setAmount(dto.getAmount());
        internetBill.setDate(dto.getDate());
        internetBill.setDescription(dto.getDescription());
        return convertToDTO(internetBillsRepository.save(internetBill));
    }

    public List<InternetBillsDTO> getAllInternetBills() {
        return internetBillsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public InternetBillsDTO updateInternetBill(Long id, InternetBillsDTO dto) {
        InternetBills internetBill = internetBillsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internet Bill not found"));
        internetBill.setAmount(dto.getAmount());
        internetBill.setDate(dto.getDate());
        internetBill.setDescription(dto.getDescription());
        return convertToDTO(internetBillsRepository.save(internetBill));
    }

    public void deleteInternetBill(Long id) {
        internetBillsRepository.deleteById(id);
    }

    private InternetBillsDTO convertToDTO(InternetBills internetBill) {
        InternetBillsDTO dto = new InternetBillsDTO();
        dto.setId(internetBill.getId());
        dto.setAmount(internetBill.getAmount());
        dto.setDate(internetBill.getDate());
        dto.setDescription(internetBill.getDescription());
        return dto;
    }
} 