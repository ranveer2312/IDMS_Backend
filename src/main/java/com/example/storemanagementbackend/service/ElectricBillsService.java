package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.dto.ElectricBillsDTO;
import com.example.storemanagementbackend.model.ElectricBills;
import com.example.storemanagementbackend.repository.ElectricBillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectricBillsService {
    private final ElectricBillsRepository electricBillsRepository;

    @Autowired
    public ElectricBillsService(ElectricBillsRepository electricBillsRepository) {
        this.electricBillsRepository = electricBillsRepository;
    }

    public ElectricBillsDTO createElectricBill(ElectricBillsDTO dto) {
        ElectricBills electricBill = new ElectricBills();
        electricBill.setAmount(dto.getAmount());
        electricBill.setDate(dto.getDate());
        electricBill.setDescription(dto.getDescription());
        
        ElectricBills savedElectricBill = electricBillsRepository.save(electricBill);
        return convertToDTO(savedElectricBill);
    }

    public List<ElectricBillsDTO> getAllElectricBills() {
        return electricBillsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ElectricBillsDTO updateElectricBill(Long id, ElectricBillsDTO dto) {
        ElectricBills electricBill = electricBillsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Electric Bill not found"));
        
        electricBill.setAmount(dto.getAmount());
        electricBill.setDate(dto.getDate());
        electricBill.setDescription(dto.getDescription());
        
        ElectricBills updatedElectricBill = electricBillsRepository.save(electricBill);
        return convertToDTO(updatedElectricBill);
    }

    public void deleteElectricBill(Long id) {
        electricBillsRepository.deleteById(id);
    }

    private ElectricBillsDTO convertToDTO(ElectricBills electricBill) {
        ElectricBillsDTO dto = new ElectricBillsDTO();
        dto.setId(electricBill.getId());
        dto.setAmount(electricBill.getAmount());
        dto.setDate(electricBill.getDate());
        dto.setDescription(electricBill.getDescription());
        return dto;
    }
} 