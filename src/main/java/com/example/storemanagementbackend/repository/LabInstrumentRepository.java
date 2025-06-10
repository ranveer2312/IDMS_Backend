package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.LabInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabInstrumentRepository extends JpaRepository<LabInstrument, Long> {
} 