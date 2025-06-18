package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.EmployeeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocument, Long> {
    List<EmployeeDocument> findByEmployeeIdAndDocumentType(String employeeId, String documentType);
    List<EmployeeDocument> findByEmployeeId(String employeeId);
    List<EmployeeDocument> findByDocumentType(String documentType);
}
 
 
 
 