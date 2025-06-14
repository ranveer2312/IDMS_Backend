package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.dto.SIMBillsDTO;
import com.example.storemanagementbackend.model.SIMBills;
import com.example.storemanagementbackend.repository.SIMBillsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SIMBillsService {
    private final SIMBillsRepository simBillsRepository;

    public SIMBillsService(SIMBillsRepository simBillsRepository) {
        this.simBillsRepository = simBillsRepository;
    }

    public SIMBillsDTO createSIMBill(SIMBillsDTO dto) {
        SIMBills simBill = new SIMBills();
        simBill.setAmount(dto.getAmount());
        simBill.setDate(dto.getDate());
        simBill.setDescription(dto.getDescription());
        return convertToDTO(simBillsRepository.save(simBill));
    }

    public List<SIMBillsDTO> getAllSIMBills() {
        return simBillsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SIMBillsDTO updateSIMBill(Long id, SIMBillsDTO dto) {
        SIMBills simBill = simBillsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SIM Bill not found"));
        simBill.setAmount(dto.getAmount());
        simBill.setDate(dto.getDate());
        simBill.setDescription(dto.getDescription());
        return convertToDTO(simBillsRepository.save(simBill));
    }

    public void deleteSIMBill(Long id) {
        simBillsRepository.deleteById(id);
    }

    private SIMBillsDTO convertToDTO(SIMBills simBill) {
        SIMBillsDTO dto = new SIMBillsDTO();
        dto.setId(simBill.getId());
        dto.setAmount(simBill.getAmount());
        dto.setDate(simBill.getDate());
        dto.setDescription(simBill.getDescription());
        return dto;
    }
} 