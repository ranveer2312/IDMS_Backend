package com.example.storemanagementbackend.repository;


import com.example.storemanagementbackend.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    // Custom query methods (if needed)
    List<Asset> findByCategory(String category);

    List<Asset> findByStatus(String status);

    List<Asset> findByAssignedToContainingIgnoreCase(String assignedTo);

    List<Asset> findByCategoryIgnoreCase(String category);

    List<Asset> findByStatusIgnoreCase(String status);
}


