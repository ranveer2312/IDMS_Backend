package com.example.storemanagementbackend.controller;
 
import com.example.storemanagementbackend.model.Employee;
import com.example.storemanagementbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
import java.util.NoSuchElementException;
 
@RestController // Marks this class as a REST controller
@RequestMapping("/api/employees") // Base path for all endpoints in this controller
@CrossOrigin(origins = "*") // Allows requests from any origin (for frontend development)
public class EmployeeController {
 
    private final EmployeeService employeeService;
 
    @Autowired // Injects the EmployeeService dependency
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
 
    /**
     * Endpoint to create a new employee.
     * @param employee The employee object to create.
     * @return ResponseEntity with the created employee and HTTP status.
     */
    @PostMapping // Handles HTTP POST requests to /api/employees
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        // @RequestBody maps the JSON request body to the Employee object
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED); // Returns 201 Created status
    }
 
    /**
     * Endpoint to get an employee by their internal ID.
     * @param id The internal ID of the employee.
     * @return ResponseEntity with the employee and HTTP status.
     */
    @GetMapping("/{id}") // Handles HTTP GET requests to /api/employees/{id}
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        // @PathVariable extracts the ID from the URL path
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK); // Returns 200 OK status
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found if employee doesn't exist
        }
    }
 
    /**
     * Endpoint to get an employee by their employee ID (e.g., EMP001).
     * @param employeeId The unique employee ID.
     * @return ResponseEntity with the employee and HTTP status.
     */
    @GetMapping("/byEmployeeId/{employeeId}") // Handles HTTP GET requests to /api/employees/byEmployeeId/{employeeId}
    public ResponseEntity<Employee> getEmployeeByEmployeeId(@PathVariable String employeeId) {
        try {
            Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    /**
     * Endpoint to get all employee records.
     * @return ResponseEntity with a list of all employees and HTTP status.
     */
    @GetMapping // Handles HTTP GET requests to /api/employees
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
 
    /**
     * Endpoint to update an existing employee record.
     * @param id The internal ID of the employee to update.
     * @param employeeDetails The updated employee details.
     * @return ResponseEntity with the updated employee and HTTP status.
     */
    @PutMapping("/{id}") // Handles HTTP PUT requests to /api/employees/{id}
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK); // Returns 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found
        }
    }
 
    /**
     * Endpoint to delete an employee record.
     * @param id The internal ID of the employee to delete.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping("/{id}") // Handles HTTP DELETE requests to /api/employees/{id}
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns 204 No Content (successful deletion)
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found
        }
    }
 
    /**
     * Endpoint to update an employee's status.
     * @param id The internal ID of the employee to update.
     * @param status The new status for the employee.
     * @return ResponseEntity with the updated employee and HTTP status.
     */
    @PutMapping("/{id}/status") // Handles HTTP PUT requests to /api/employees/{id}/status
    public ResponseEntity<Employee> updateEmployeeStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Employee updatedEmployee = employeeService.updateEmployeeStatus(id, status);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
 
 
 