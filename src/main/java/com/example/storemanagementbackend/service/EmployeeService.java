package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    Employee getEmployeeById(Long id);
    Employee getEmployeeByEmployeeId(String employeeId);
    List<Employee> getAllEmployees();
    Employee updateEmployeeStatus(Long id, String status);
}
 
 