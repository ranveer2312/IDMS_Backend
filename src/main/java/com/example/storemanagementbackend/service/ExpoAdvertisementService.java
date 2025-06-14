package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.dto.ExpoAdvertisementDTO;
import com.example.storemanagementbackend.model.ExpoAdvertisement;
import com.example.storemanagementbackend.repository.ExpoAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpoAdvertisementService {
    private final ExpoAdvertisementRepository expoAdvertisementRepository;

    @Autowired
    public ExpoAdvertisementService(ExpoAdvertisementRepository expoAdvertisementRepository) {
        this.expoAdvertisementRepository = expoAdvertisementRepository;
    }

    public ExpoAdvertisementDTO createExpoAdvertisement(ExpoAdvertisementDTO dto) {
        ExpoAdvertisement expoAdvertisement = new ExpoAdvertisement();
        expoAdvertisement.setAmount(dto.getAmount());
        expoAdvertisement.setDate(dto.getDate());
        expoAdvertisement.setDescription(dto.getDescription());
        
        ExpoAdvertisement savedExpoAdvertisement = expoAdvertisementRepository.save(expoAdvertisement);
        return convertToDTO(savedExpoAdvertisement);
    }

    public List<ExpoAdvertisementDTO> getAllExpoAdvertisements() {
        return expoAdvertisementRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ExpoAdvertisementDTO updateExpoAdvertisement(Long id, ExpoAdvertisementDTO dto) {
        ExpoAdvertisement expoAdvertisement = expoAdvertisementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expo Advertisement not found"));
        
        expoAdvertisement.setAmount(dto.getAmount());
        expoAdvertisement.setDate(dto.getDate());
        expoAdvertisement.setDescription(dto.getDescription());
        
        ExpoAdvertisement updatedExpoAdvertisement = expoAdvertisementRepository.save(expoAdvertisement);
        return convertToDTO(updatedExpoAdvertisement);
    }

    public void deleteExpoAdvertisement(Long id) {
        expoAdvertisementRepository.deleteById(id);
    }

    private ExpoAdvertisementDTO convertToDTO(ExpoAdvertisement expoAdvertisement) {
        ExpoAdvertisementDTO dto = new ExpoAdvertisementDTO();
        dto.setId(expoAdvertisement.getId());
        dto.setAmount(expoAdvertisement.getAmount());
        dto.setDate(expoAdvertisement.getDate());
        dto.setDescription(expoAdvertisement.getDescription());
        return dto;
    }
} 