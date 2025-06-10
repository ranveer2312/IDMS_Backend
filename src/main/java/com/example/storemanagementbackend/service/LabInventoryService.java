package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.LabInventory;
import com.example.storemanagementbackend.repository.LabInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LabInventoryService {
    @Autowired
    private LabInventoryRepository repository;

    public List<LabInventory> findAll() {
        return repository.findAll();
    }

    public Optional<LabInventory> findById(Long id) {
        return repository.findById(id);
    }

    public LabInventory save(LabInventory inventory) {
        return repository.save(inventory);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
} 