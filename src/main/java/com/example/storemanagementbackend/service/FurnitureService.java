package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.Furniture;
import com.example.storemanagementbackend.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FurnitureService {
    @Autowired
    private FurnitureRepository repository;

    public List<Furniture> findAll() {
        return repository.findAll();
    }

    public Optional<Furniture> findById(Long id) {
        return repository.findById(id);
    }

    public Furniture save(Furniture furniture) {
        return repository.save(furniture);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
} 