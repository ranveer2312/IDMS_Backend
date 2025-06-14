package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.ExpoAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpoAdvertisementRepository extends JpaRepository<ExpoAdvertisement, Long> {
} 