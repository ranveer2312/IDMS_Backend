// PerformanceController.java
package com.example.storemanagementbackend.controller;
 
import com.example.storemanagementbackend.model.Performance;
import com.example.storemanagementbackend.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
/**
 * REST controller for managing Performance records.
 * Provides endpoints for CRUD operations on Performance entities.
 */
@RestController
@RequestMapping("/api/performance")
@CrossOrigin(origins = "http://localhost:3000") // Allows frontend to access this API
public class PerformanceController {
 
    @Autowired
    private PerformanceService performanceService;
 
    /**
     * Retrieves all performance records.
     *
     * @return A list of Performance objects.
     */
    @GetMapping
    public ResponseEntity<List<Performance>> getAllPerformances() {
        List<Performance> performances = performanceService.getAllPerformances();
        return ResponseEntity.ok(performances);
    }
 
    /**
     * Retrieves a performance record by its ID.
     *
     * @param id The ID of the performance record to retrieve.
     * @return The Performance object if found, or a 404 Not Found response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Performance> getPerformanceById(@PathVariable Long id) {
        return performanceService.getPerformanceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
 
    /**
     * Creates a new performance record.
     *
     * @param performance The Performance object to be created.
     * @return The created Performance object and a 201 Created status.
     */
    @PostMapping
    public ResponseEntity<Performance> createPerformance(@RequestBody Performance performance) {
        Performance createdPerformance = performanceService.createPerformance(performance);
        return new ResponseEntity<>(createdPerformance, HttpStatus.CREATED);
    }
 
    /**
     * Updates an existing performance record.
     *
     * @param id                 The ID of the performance record to update.
     * @param performanceDetails The updated Performance object.
     * @return The updated Performance object if successful, or a 404 Not Found
     *         response.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Performance> updatePerformance(@PathVariable Long id,
            @RequestBody Performance performanceDetails) {
        try {
            Performance updatedPerformance = performanceService.updatePerformance(id, performanceDetails);
            return ResponseEntity.ok(updatedPerformance);
        } catch (RuntimeException e) {
            // Log the error for debugging purposes (consider using a proper logger like
            // SLF4J)
            System.err.println("Error updating performance for ID " + id + ": " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
 
    /**
     * Deletes a performance record by its ID.
     *
     * @param id The ID of the performance record to delete.
     * @return A 204 No Content response if successful, or a 404 Not Found response
     *         if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformance(@PathVariable Long id) {
        try {
            performanceService.deletePerformance(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Log the error for debugging purposes
            System.err.println("Error deleting performance with ID " + id + ": " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
 