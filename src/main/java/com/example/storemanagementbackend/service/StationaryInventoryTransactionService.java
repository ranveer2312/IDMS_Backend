package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.StationaryInventoryTransaction;
import com.example.storemanagementbackend.repository.StationaryInventoryTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StationaryInventoryTransactionService {
    @Autowired
    private StationaryInventoryTransactionRepository repository;

    public List<StationaryInventoryTransaction> findAll() {
        return repository.findAll();
    }

    public Optional<StationaryInventoryTransaction> findById(Long id) {
        return repository.findById(id);
    }

    public StationaryInventoryTransaction save(StationaryInventoryTransaction transaction) {
        return repository.save(transaction);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
} 