package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.dto.WaterBillsDTO;
import com.example.storemanagementbackend.model.WaterBills;
import com.example.storemanagementbackend.repository.WaterBillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WaterBillsService {
    private final WaterBillsRepository waterBillsRepository;

    @Autowired
    public WaterBillsService(WaterBillsRepository waterBillsRepository) {
        this.waterBillsRepository = waterBillsRepository;
    }

    public WaterBillsDTO createWaterBill(WaterBillsDTO dto) {
        WaterBills waterBill = new WaterBills();
        waterBill.setAmount(dto.getAmount());
        waterBill.setDate(dto.getDate());
        waterBill.setDescription(dto.getDescription());
        
        WaterBills savedWaterBill = waterBillsRepository.save(waterBill);
        return convertToDTO(savedWaterBill);
    }

    public List<WaterBillsDTO> getAllWaterBills() {
        return waterBillsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public WaterBillsDTO updateWaterBill(Long id, WaterBillsDTO dto) {
        WaterBills waterBill = waterBillsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Water Bill not found"));
        
        waterBill.setAmount(dto.getAmount());
        waterBill.setDate(dto.getDate());
        waterBill.setDescription(dto.getDescription());
        
        WaterBills updatedWaterBill = waterBillsRepository.save(waterBill);
        return convertToDTO(updatedWaterBill);
    }

    public void deleteWaterBill(Long id) {
        waterBillsRepository.deleteById(id);
    }

    private WaterBillsDTO convertToDTO(WaterBills waterBill) {
        WaterBillsDTO dto = new WaterBillsDTO();
        dto.setId(waterBill.getId());
        dto.setAmount(waterBill.getAmount());
        dto.setDate(waterBill.getDate());
        dto.setDescription(waterBill.getDescription());
        return dto;
    }
} 