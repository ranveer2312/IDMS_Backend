package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.LabMaterial;
import com.example.storemanagementbackend.repository.LabMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LabMaterialService {
    @Autowired
    private LabMaterialRepository repository;

    public List<LabMaterial> findAll() {
        return repository.findAll();
    }

    public Optional<LabMaterial> findById(Long id) {
        return repository.findById(id);
    }

    public LabMaterial save(LabMaterial material) {
        return repository.save(material);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
} 