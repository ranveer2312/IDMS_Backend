package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.FixedStationary;
import com.example.storemanagementbackend.repository.FixedStationaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FixedStationaryService {
    @Autowired
    private FixedStationaryRepository repository;

    public List<FixedStationary> findAll() {
        return repository.findAll();
    }

    public Optional<FixedStationary> findById(Long id) {
        return repository.findById(id);
    }

    public FixedStationary save(FixedStationary stationary) {
        return repository.save(stationary);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
} 