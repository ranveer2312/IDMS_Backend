package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.dto.SalariesDTO;
import com.example.storemanagementbackend.model.Salaries;
import com.example.storemanagementbackend.repository.SalariesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalariesService {
    private final SalariesRepository salariesRepository;

    public SalariesService(SalariesRepository salariesRepository) {
        this.salariesRepository = salariesRepository;
    }

    public SalariesDTO createSalary(SalariesDTO dto) {
        Salaries salary = new Salaries();
        salary.setAmount(dto.getAmount());
        salary.setDate(dto.getDate());
        salary.setDescription(dto.getDescription());
        return convertToDTO(salariesRepository.save(salary));
    }

    public List<SalariesDTO> getAllSalaries() {
        return salariesRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SalariesDTO updateSalary(Long id, SalariesDTO dto) {
        Salaries salary = salariesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary not found"));
        salary.setAmount(dto.getAmount());
        salary.setDate(dto.getDate());
        salary.setDescription(dto.getDescription());
        return convertToDTO(salariesRepository.save(salary));
    }

    public void deleteSalary(Long id) {
        salariesRepository.deleteById(id);
    }

    private SalariesDTO convertToDTO(Salaries salary) {
        SalariesDTO dto = new SalariesDTO();
        dto.setId(salary.getId());
        dto.setAmount(salary.getAmount());
        dto.setDate(salary.getDate());
        dto.setDescription(salary.getDescription());
        return dto;
    }
} 