package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.Incentives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncentivesRepository extends JpaRepository<Incentives, Long> {
} 