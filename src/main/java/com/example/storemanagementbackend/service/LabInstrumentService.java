package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.LabInstrument;
import com.example.storemanagementbackend.repository.LabInstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LabInstrumentService {
    @Autowired
    private LabInstrumentRepository repository;

    public List<LabInstrument> findAll() {
        return repository.findAll();
    }

    public Optional<LabInstrument> findById(Long id) {
        return repository.findById(id);
    }

    public LabInstrument save(LabInstrument instrument) {
        return repository.save(instrument);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
} 