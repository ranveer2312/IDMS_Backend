package com.example.storemanagementbackend.service.impl;
 
import com.example.storemanagementbackend.model.Employee;
import com.example.storemanagementbackend.repository.EmployeeRepository;
import com.example.storemanagementbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException; // For handling not found cases
 
@Service // Marks this class as a Spring service component
public class EmployeeServiceImpl implements EmployeeService {
 
    private final EmployeeRepository employeeRepository;
 
    @Autowired // Injects the EmployeeRepository dependency
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
 
    @Override
    @Transactional // Ensures the entire method runs as a single transaction
    public Employee createEmployee(Employee employee) {
        // In a real application:
        // 1. Hash the password before saving (e.g., using BCryptPasswordEncoder)
        // 2. Validate input fields (e.g., email format, required fields)
        // 3. Set default status, joining date if not provided
        if (employee.getJoiningDate() == null) {
            employee.setJoiningDate(LocalDate.now());
        }
        if (employee.getStatus() == null || employee.getStatus().isEmpty()) {
            employee.setStatus("Active"); // Default status for new employees
        }
        return employeeRepository.save(employee);
    }
 
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));
    }
 
    @Override
    public Employee getEmployeeByEmployeeId(String employeeId) {
        return employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with employee ID: " + employeeId));
    }
 
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
 
    @Override
    @Transactional
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));
 
        // Update fields based on employeeDetails.
        // You might want to add null checks or specific business logic here.
        existingEmployee.setEmployeeName(employeeDetails.getEmployeeName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setPhoneNumber(employeeDetails.getPhoneNumber());
        existingEmployee.setBloodGroup(employeeDetails.getBloodGroup());
        existingEmployee.setProfilePhotoUrl(employeeDetails.getProfilePhotoUrl());
        existingEmployee.setCurrentAddress(employeeDetails.getCurrentAddress());
        existingEmployee.setPermanentAddress(employeeDetails.getPermanentAddress());
        // Password should be handled carefully, only update if a new one is provided and hashed
        if (employeeDetails.getPassword() != null && !employeeDetails.getPassword().isEmpty()) {
            // In a real app: existingEmployee.setPassword(passwordEncoder.encode(employeeDetails.getPassword()));
            existingEmployee.setPassword(employeeDetails.getPassword()); // For demo only, DO NOT do this in production
        }
        existingEmployee.setPosition(employeeDetails.getPosition());
        existingEmployee.setDepartment(employeeDetails.getDepartment());
        existingEmployee.setJoiningDate(employeeDetails.getJoiningDate());
        existingEmployee.setRelievingDate(employeeDetails.getRelievingDate());
        existingEmployee.setStatus(employeeDetails.getStatus());
 
        return employeeRepository.save(existingEmployee);
    }
 
    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new NoSuchElementException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
 
    @Override
    @Transactional
    public Employee updateEmployeeStatus(Long id, String status) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));
        employee.setStatus(status);
        return employeeRepository.save(employee);
    }
}
 
 