package com.example.storemanagementbackend.repository;
 
import com.example.storemanagementbackend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    // Custom query methods (Spring Data JPA automatically implements these)
    List<Report> findByType(String type);
 
    List<Report> findByTypeAndSubtype(String type, String subtype);
}
 