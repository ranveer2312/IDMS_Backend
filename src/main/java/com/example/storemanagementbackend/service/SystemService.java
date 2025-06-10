package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.System;
import com.example.storemanagementbackend.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SystemService {
    @Autowired
    private SystemRepository repository;

    public List<System> findAll() {
        return repository.findAll();
    }

    public Optional<System> findById(Long id) {
        return repository.findById(id);
    }

    public System save(System system) {
        return repository.save(system);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
} 
