package com.example.storemanagementbackend.repository;

import com.example.storemanagementbackend.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    
    // Find memos sent by a specific admin
    List<Memo> findBySentByOrderBySentAtDesc(String sentBy);
    
    // Find memos sent to a specific employee
    @Query("SELECT m FROM Memo m WHERE m.sentToAll = true OR :employeeId MEMBER OF m.recipientEmployeeIds OR :department MEMBER OF m.recipientDepartments")
    List<Memo> findMemosForEmployee(@Param("employeeId") String employeeId, @Param("department") String department);
    
    // Find memos sent to a specific department
    @Query("SELECT m FROM Memo m WHERE m.sentToAll = true OR :department MEMBER OF m.recipientDepartments")
    List<Memo> findMemosForDepartment(@Param("department") String department);
    
    // Find all memos ordered by sent date (newest first)
    List<Memo> findAllByOrderBySentAtDesc();
} 