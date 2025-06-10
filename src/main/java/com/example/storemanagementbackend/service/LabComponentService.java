package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.LabComponent;
import com.example.storemanagementbackend.repository.LabComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LabComponentService {
    @Autowired
    private LabComponentRepository repository;

    public List<LabComponent> findAll() {
        return repository.findAll();
    }

    public Optional<LabComponent> findById(Long id) {
        return repository.findById(id);
    }

    public LabComponent save(LabComponent component) {
        return repository.save(component);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
} 