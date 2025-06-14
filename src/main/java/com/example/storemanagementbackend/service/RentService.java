package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.dto.RentDTO;
import com.example.storemanagementbackend.model.Rent;
import com.example.storemanagementbackend.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentService {
    private final RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public RentDTO createRent(RentDTO dto) {
        Rent rent = new Rent();
        rent.setAmount(dto.getAmount());
        rent.setDate(dto.getDate());
        rent.setDescription(dto.getDescription());
        return convertToDTO(rentRepository.save(rent));
    }

    public List<RentDTO> getAllRents() {
        return rentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RentDTO updateRent(Long id, RentDTO dto) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rent not found"));
        rent.setAmount(dto.getAmount());
        rent.setDate(dto.getDate());
        rent.setDescription(dto.getDescription());
        return convertToDTO(rentRepository.save(rent));
    }

    public void deleteRent(Long id) {
        rentRepository.deleteById(id);
    }

    private RentDTO convertToDTO(Rent rent) {
        RentDTO dto = new RentDTO();
        dto.setId(rent.getId());
        dto.setAmount(rent.getAmount());
        dto.setDate(rent.getDate());
        dto.setDescription(rent.getDescription());
        return dto;
    }
} 