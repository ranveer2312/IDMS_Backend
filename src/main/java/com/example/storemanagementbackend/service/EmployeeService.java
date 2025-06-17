package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    Employee getEmployeeByEmployeeId(String employeeId);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Long id, Employee employeeDetails);
    void deleteEmployee(Long id);
    Employee updateEmployeeStatus(Long id, String status);
}
 
 
 