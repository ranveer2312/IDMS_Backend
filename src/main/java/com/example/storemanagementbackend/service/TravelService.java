package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.dto.TravelDTO;
import com.example.storemanagementbackend.model.Travel;
import com.example.storemanagementbackend.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelService {
    private final TravelRepository travelRepository;

    @Autowired
    public TravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    public TravelDTO createTravel(TravelDTO dto) {
        Travel travel = new Travel();
        travel.setAmount(dto.getAmount());
        travel.setDate(dto.getDate());
        travel.setDescription(dto.getDescription());
        
        Travel savedTravel = travelRepository.save(travel);
        return convertToDTO(savedTravel);
    }

    public List<TravelDTO> getAllTravels() {
        return travelRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TravelDTO updateTravel(Long id, TravelDTO dto) {
        Travel travel = travelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel not found"));
        
        travel.setAmount(dto.getAmount());
        travel.setDate(dto.getDate());
        travel.setDescription(dto.getDescription());
        
        Travel updatedTravel = travelRepository.save(travel);
        return convertToDTO(updatedTravel);
    }

    public void deleteTravel(Long id) {
        travelRepository.deleteById(id);
    }

    private TravelDTO convertToDTO(Travel travel) {
        TravelDTO dto = new TravelDTO();
        dto.setId(travel.getId());
        dto.setAmount(travel.getAmount());
        dto.setDate(travel.getDate());
        dto.setDescription(travel.getDescription());
        return dto;
    }
} 