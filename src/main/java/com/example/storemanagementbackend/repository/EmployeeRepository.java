package com.example.storemanagementbackend.repository;
 
import com.example.storemanagementbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
import java.util.Optional;
 
@Repository // Marks this interface as a Spring Data JPA repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // JpaRepository provides basic CRUD operations: save, findById, findAll, delete, etc.
 
    // Custom method to find an employee by their unique employeeId
    Optional<Employee> findByEmployeeId(String employeeId);
 
    // Custom method to find an employee by email (assuming email is also unique)
    Optional<Employee> findByEmail(String email);
    
    // Custom method to find employees by department
    List<Employee> findByDepartment(String department);
}
 
 