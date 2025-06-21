package com.example.storemanagementbackend.controller;

import com.example.storemanagementbackend.dto.MemoRequestDTO;
import com.example.storemanagementbackend.dto.MemoResponseDTO;
import com.example.storemanagementbackend.dto.EmployeeInfoDTO;
import com.example.storemanagementbackend.service.MemoService;
import com.example.storemanagementbackend.model.Employee;
import com.example.storemanagementbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/memos")
@CrossOrigin(origins = "http://localhost:3000")
public class MemoController {

    @Autowired
    private MemoService memoService;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    // POST - Create a new memo (Admin only)
    @PostMapping
    public ResponseEntity<MemoResponseDTO> createMemo(@RequestBody MemoRequestDTO memoRequestDTO) {
        try {
            MemoResponseDTO createdMemo = memoService.createMemo(memoRequestDTO);
            return new ResponseEntity<>(createdMemo, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // GET - Get all memos (Admin only)
    @GetMapping
    public ResponseEntity<List<MemoResponseDTO>> getAllMemos() {
        List<MemoResponseDTO> memos = memoService.getAllMemos();
        return ResponseEntity.ok(memos);
    }

    // GET - Get a specific memo by ID
    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDTO> getMemoById(@PathVariable Long id) {
        try {
            MemoResponseDTO memo = memoService.getMemoById(id);
            return ResponseEntity.ok(memo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Get memos for a specific employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<MemoResponseDTO>> getMemosByEmployee(@PathVariable String employeeId) {
        List<MemoResponseDTO> memos = memoService.getMemosByEmployee(employeeId);
        return ResponseEntity.ok(memos);
    }

    // GET - Get memos for a specific department
    @GetMapping("/department/{department}")
    public ResponseEntity<List<MemoResponseDTO>> getMemosByDepartment(@PathVariable String department) {
        List<MemoResponseDTO> memos = memoService.getMemosByDepartment(department);
        return ResponseEntity.ok(memos);
    }

    // GET - Get memos sent by a specific admin
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<MemoResponseDTO>> getMemosByAdmin(@PathVariable String adminId) {
        List<MemoResponseDTO> memos = memoService.getMemosByAdmin(adminId);
        return ResponseEntity.ok(memos);
    }

    // PUT - Update an existing memo
    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDTO> updateMemo(@PathVariable Long id, @RequestBody MemoRequestDTO memoRequestDTO) {
        try {
            MemoResponseDTO updatedMemo = memoService.updateMemo(id, memoRequestDTO);
            return ResponseEntity.ok(updatedMemo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Delete a memo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {
        try {
            memoService.deleteMemo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Get all departments (for dropdown in frontend)
    @GetMapping("/departments")
    public ResponseEntity<List<String>> getAllDepartments() {
        List<String> departments = employeeRepository.findAll().stream()
                .map(Employee::getDepartment)
                .distinct()
                .filter(dept -> dept != null && !dept.trim().isEmpty())
                .collect(Collectors.toList());
        return ResponseEntity.ok(departments);
    }
    
    // GET - Get all employees (for dropdown in frontend)
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeInfoDTO>> getAllEmployees() {
        List<EmployeeInfoDTO> employees = employeeRepository.findAll().stream()
                .map(emp -> {
                    EmployeeInfoDTO dto = new EmployeeInfoDTO();
                    dto.setEmployeeId(emp.getEmployeeId());
                    dto.setEmployeeName(emp.getEmployeeName());
                    dto.setDepartment(emp.getDepartment());
                    dto.setEmail(emp.getEmail());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(employees);
    }
} 