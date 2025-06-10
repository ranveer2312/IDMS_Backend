package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.LabInventoryTransaction;
import com.example.storemanagementbackend.repository.LabInventoryTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LabInventoryTransactionService {
    @Autowired
    private LabInventoryTransactionRepository repository;

    public List<LabInventoryTransaction> findAll() {
        return repository.findAll();
    }

    public Optional<LabInventoryTransaction> findById(Long id) {
        return repository.findById(id);
    }

    public LabInventoryTransaction save(LabInventoryTransaction transaction) {
        return repository.save(transaction);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
} 