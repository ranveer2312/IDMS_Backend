package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.Printer;
import com.example.storemanagementbackend.repository.PrinterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PrinterService {
    @Autowired
    private PrinterRepository repository;

    public List<Printer> findAll() {
        return repository.findAll();
    }

    public Optional<Printer> findById(Long id) {
        return repository.findById(id);
    }

    public Printer save(Printer printer) {
        return repository.save(printer);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
} 
