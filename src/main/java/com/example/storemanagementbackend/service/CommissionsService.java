package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.dto.CommissionsDTO;
import com.example.storemanagementbackend.model.Commission;
import com.example.storemanagementbackend.repository.CommissionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommissionsService {
    private final CommissionsRepository commissionsRepository;

    public CommissionsService(CommissionsRepository commissionsRepository) {
        this.commissionsRepository = commissionsRepository;
    }

    public CommissionsDTO createCommission(CommissionsDTO dto) {
        Commission commission = new Commission();
        commission.setAmount(dto.getAmount());
        commission.setDate(dto.getDate());
        commission.setDescription(dto.getDescription());
        commission.setRecipient(dto.getRecipient());
        return convertToDTO(commissionsRepository.save(commission));
    }

    public List<CommissionsDTO> getAllCommissions() {
        return commissionsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CommissionsDTO updateCommission(Long id, CommissionsDTO dto) {
        Commission commission = commissionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commission not found"));
        commission.setAmount(dto.getAmount());
        commission.setDate(dto.getDate());
        commission.setDescription(dto.getDescription());
        commission.setRecipient(dto.getRecipient());
        return convertToDTO(commissionsRepository.save(commission));
    }

    public void deleteCommission(Long id) {
        commissionsRepository.deleteById(id);
    }

    private CommissionsDTO convertToDTO(Commission commission) {
        CommissionsDTO dto = new CommissionsDTO();
        dto.setId(commission.getId());
        dto.setAmount(commission.getAmount());
        dto.setDate(commission.getDate());
        dto.setDescription(commission.getDescription());
        dto.setRecipient(commission.getRecipient());
        return dto;
    }
} 