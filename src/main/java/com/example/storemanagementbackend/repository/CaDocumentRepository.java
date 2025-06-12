package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.CaDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaDocumentRepository extends JpaRepository<CaDocument, Long> {
} 