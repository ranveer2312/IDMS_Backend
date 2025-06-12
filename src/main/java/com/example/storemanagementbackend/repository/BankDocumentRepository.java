package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.BankDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDocumentRepository extends JpaRepository<BankDocument, Long> {
} 