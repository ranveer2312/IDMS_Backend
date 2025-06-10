package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.RegularStationary;
import com.example.storemanagementbackend.repository.RegularStationaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RegularStationaryService {
    @Autowired
    private RegularStationaryRepository repository;

    public List<RegularStationary> findAll() {
        return repository.findAll();
    }

    public Optional<RegularStationary> findById(Long id) {
        return repository.findById(id);
    }

    public RegularStationary save(RegularStationary stationary) {
        return repository.save(stationary);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
} 