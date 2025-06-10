package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.RegularStationary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularStationaryRepository extends JpaRepository<RegularStationary, Long> {
} 