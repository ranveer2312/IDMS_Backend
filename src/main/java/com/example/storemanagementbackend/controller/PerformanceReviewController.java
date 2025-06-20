package com.example.storemanagementbackend.controller;
 
import com.example.storemanagementbackend.dto.PerformanceReviewDTO;
import com.example.storemanagementbackend.model.PerformanceReview;
import com.example.storemanagementbackend.service.PerformanceReviewService;
import com.example.storemanagementbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
import java.util.NoSuchElementException;
 
/**
 * REST Controller for managing PerformanceReview entities.
 * Handles HTTP requests related to creating, retrieving, updating, and deleting performance reviews.
 */
@RestController // Marks this class as a REST controller
@RequestMapping("/api/performance-reviews") // Base path for all endpoints in this controller
@CrossOrigin(origins = "http://localhost:3000") // Allows requests from the specified origin
public class PerformanceReviewController {
 
    private final PerformanceReviewService performanceReviewService;
    private final EmployeeService employeeService;
 
    @Autowired // Injects the PerformanceReviewService and EmployeeService dependencies
    public PerformanceReviewController(PerformanceReviewService performanceReviewService, EmployeeService employeeService) {
        this.performanceReviewService = performanceReviewService;
        this.employeeService = employeeService;
    }
 
    /**
     * Endpoint to create a new performance review.
     * The request body should contain the PerformanceReviewDTO object, including the 'employeeId'
     * to link the review to an existing employee.
     * @param performanceReviewDTO The PerformanceReviewDTO object to create.
     * @return ResponseEntity with the created performance review and HTTP status.
     */
    @PostMapping // Handles HTTP POST requests to /api/performance-reviews
    public ResponseEntity<PerformanceReview> createPerformanceReview(@RequestBody PerformanceReviewDTO performanceReviewDTO) {
        try {
            PerformanceReview createdReview = performanceReviewService.createPerformanceReview(performanceReviewDTO);
            return new ResponseEntity<>(createdReview, HttpStatus.CREATED); // Returns 201 Created status
        } catch (NoSuchElementException e) {
            // If the employee associated with the review is not found
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Or HttpStatus.UNPROCESSABLE_ENTITY
        } catch (IllegalArgumentException e) {
            // If employee ID is missing from the request
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
 
    /**
     * Endpoint to get a single performance review by its unique ID.
     * @param id The ID of the performance review.
     * @return ResponseEntity with the performance review and HTTP status.
     */
    @GetMapping("/{id}") // Handles HTTP GET requests to /api/performance-reviews/{id}
    public ResponseEntity<PerformanceReview> getPerformanceReviewById(@PathVariable Long id) {
        try {
            PerformanceReview review = performanceReviewService.getPerformanceReviewById(id);
            return new ResponseEntity<>(review, HttpStatus.OK); // Returns 200 OK status
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found if review doesn't exist
        }
    }
 
    /**
     * Endpoint to get all performance reviews in the system.
     * @return ResponseEntity with a list of all performance reviews and HTTP status.
     */
    @GetMapping // Handles HTTP GET requests to /api/performance-reviews
    public ResponseEntity<List<PerformanceReview>> getAllPerformanceReviews() {
        List<PerformanceReview> reviews = performanceReviewService.getAllPerformanceReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
 
    /**
     * Endpoint to get all performance reviews for a specific employee.
     * This is useful for displaying all reviews on an employee's profile page.
     * @param employeeId The ID of the employee.
     * @return ResponseEntity with a list of performance reviews for the employee and HTTP status.
     */
    @GetMapping("/employee/{employeeId}") // Handles HTTP GET requests to /api/performance-reviews/employee/{employeeId}
    public ResponseEntity<List<PerformanceReview>> getPerformanceReviewsByEmployeeId(@PathVariable String employeeId) {
        try {
            List<PerformanceReview> reviews = performanceReviewService.getPerformanceReviewsByEmployeeId(employeeId);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Employee not found or no reviews for employee
        }
    }
 
    /**
     * Endpoint to get all performance reviews for a specific employee by numeric ID.
     * @param id The internal numeric ID of the employee.
     * @return ResponseEntity with a list of performance reviews for the employee and HTTP status.
     */
    @GetMapping("/employee/byId/{id}")
    public ResponseEntity<List<PerformanceReview>> getPerformanceReviewsByEmployeeDbId(@PathVariable Long id) {
        try {
            var employee = employeeService.getEmployeeById(id);
            var reviews = performanceReviewService.getPerformanceReviewsByEmployeeId(employee.getEmployeeId());
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    /**
     * Endpoint to update an existing performance review.
     * @param id The ID of the performance review to update.
     * @param performanceReviewDetails The PerformanceReview object with updated details.
     * @return ResponseEntity with the updated performance review and HTTP status.
     */
    @PutMapping("/{id}") // Handles HTTP PUT requests to /api/performance-reviews/{id}
    public ResponseEntity<PerformanceReview> updatePerformanceReview(@PathVariable Long id, @RequestBody PerformanceReview performanceReviewDetails) {
        try {
            PerformanceReview updatedReview = performanceReviewService.updatePerformanceReview(id, performanceReviewDetails);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK); // Returns 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found
        }
    }
 
    /**
     * Endpoint to delete a performance review.
     * @param id The ID of the performance review to delete.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping("/{id}") // Handles HTTP DELETE requests to /api/performance-reviews/{id}
    public ResponseEntity<HttpStatus> deletePerformanceReview(@PathVariable Long id) {
        try {
            performanceReviewService.deletePerformanceReview(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns 204 No Content (successful deletion)
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found
        }
    }
}
 
 
 