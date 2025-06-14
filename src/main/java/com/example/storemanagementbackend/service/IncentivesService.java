package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.dto.IncentivesDTO;
import com.example.storemanagementbackend.model.Incentives;
import com.example.storemanagementbackend.repository.IncentivesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncentivesService {
    private final IncentivesRepository incentivesRepository;

    public IncentivesService(IncentivesRepository incentivesRepository) {
        this.incentivesRepository = incentivesRepository;
    }

    public IncentivesDTO createIncentive(IncentivesDTO dto) {
        Incentives incentive = new Incentives();
        incentive.setAmount(dto.getAmount());
        incentive.setDate(dto.getDate());
        incentive.setDescription(dto.getDescription());
        return convertToDTO(incentivesRepository.save(incentive));
    }

    public List<IncentivesDTO> getAllIncentives() {
        return incentivesRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public IncentivesDTO updateIncentive(Long id, IncentivesDTO dto) {
        Incentives incentive = incentivesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incentive not found"));
        incentive.setAmount(dto.getAmount());
        incentive.setDate(dto.getDate());
        incentive.setDescription(dto.getDescription());
        return convertToDTO(incentivesRepository.save(incentive));
    }

    public void deleteIncentive(Long id) {
        incentivesRepository.deleteById(id);
    }

    private IncentivesDTO convertToDTO(Incentives incentive) {
        IncentivesDTO dto = new IncentivesDTO();
        dto.setId(incentive.getId());
        dto.setAmount(incentive.getAmount());
        dto.setDate(incentive.getDate());
        dto.setDescription(incentive.getDescription());
        return dto;
    }
} 