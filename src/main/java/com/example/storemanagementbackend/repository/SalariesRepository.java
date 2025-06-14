package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.Salaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalariesRepository extends JpaRepository<Salaries, Long> {
} 