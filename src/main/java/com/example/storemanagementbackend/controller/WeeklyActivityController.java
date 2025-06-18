package com.example.storemanagementbackend.controller;
 
import com.example.storemanagementbackend.model.WeeklyActivity;
import com.example.storemanagementbackend.service.WeeklyActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
import java.util.NoSuchElementException;
 
/**
 * REST Controller for managing WeeklyActivity entities.
 * Handles HTTP requests related to creating, retrieving, updating, and deleting weekly activities.
 */
@RestController // Marks this class as a REST controller
@RequestMapping("/api/activities") // Base path for all endpoints in this controller
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true") // Allows requests from the specified origin with credentials
public class WeeklyActivityController {
 
    private final WeeklyActivityService weeklyActivityService;
 
    @Autowired // Injects the WeeklyActivityService dependency
    public WeeklyActivityController(WeeklyActivityService weeklyActivityService) {
        this.weeklyActivityService = weeklyActivityService;
    }
 
    /**
     * Endpoint to create a new weekly activity.
//     * @param weeklyActivity The WeeklyActivity object to create.
     * @return ResponseEntity with the created weekly activity and HTTP status.
     */
    @PostMapping // Handles HTTP POST requests to /api/activities
    public ResponseEntity<WeeklyActivity> createWeeklyActivity(@RequestBody WeeklyActivity weeklyActivity) {
        try {
            WeeklyActivity createdActivity = weeklyActivityService.createWeeklyActivity(weeklyActivity);
            return new ResponseEntity<>(createdActivity, HttpStatus.CREATED); // Returns 201 Created status
        } catch (IllegalArgumentException e) {
            // Handle validation errors (e.g., missing title/date)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
 
    /**
     * Endpoint to get a single weekly activity by its unique ID.
//     * @param id The ID of the weekly activity.
     * @return ResponseEntity with the weekly activity and HTTP status.
     */
    @GetMapping("/{id}") // Handles HTTP GET requests to /api/activities/{id}
    public ResponseEntity<WeeklyActivity> getWeeklyActivityById(@PathVariable Long id) {
        try {
            WeeklyActivity activity = weeklyActivityService.getWeeklyActivityById(id);
            return new ResponseEntity<>(activity, HttpStatus.OK); // Returns 200 OK status
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found if activity doesn't exist
        }
    }
 
    /**
     * Endpoint to get all weekly activities in the system, with optional search and filter.
//     * @param searchTerm Optional. Term to search for in title or description.
//     * @param category Optional. Category to filter activities by.
//     * @param status Optional. Status to filter activities by.
//     * @return ResponseEntity with a list of weekly activities and HTTP status.
     */
    @GetMapping // Handles HTTP GET requests to /api/activities
    public ResponseEntity<List<WeeklyActivity>> getFilteredWeeklyActivities(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String status) {
 
        List<WeeklyActivity> activities;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            activities = weeklyActivityService.searchWeeklyActivities(searchTerm);
        } else if (category != null && !category.isEmpty()) {
            activities = weeklyActivityService.getWeeklyActivitiesByCategory(category);
        } else if (status != null && !status.isEmpty()) {
            activities = weeklyActivityService.getWeeklyActivitiesByStatus(status);
        } else {
            activities = weeklyActivityService.getAllWeeklyActivities();
        }
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }
 
    /**
     * Endpoint to update an existing weekly activity.
     * @param id The ID of the weekly activity to update.
     * @param weeklyActivityDetails The WeeklyActivity object with updated details.
     * @return ResponseEntity with the updated weekly activity and HTTP status.
     */
    @PutMapping("/{id}") // Handles HTTP PUT requests to /api/activities/{id}
    public ResponseEntity<WeeklyActivity> updateWeeklyActivity(@PathVariable Long id, @RequestBody WeeklyActivity weeklyActivityDetails) {
        try {
            WeeklyActivity updatedActivity = weeklyActivityService.updateWeeklyActivity(id, weeklyActivityDetails);
            return new ResponseEntity<>(updatedActivity, HttpStatus.OK); // Returns 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found
        }
    }
 
    /**
     * Endpoint to delete a weekly activity.
//     * @param id The ID of the weekly activity to delete.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping("/{id}") // Handles HTTP DELETE requests to /api/activities/{id}
    public ResponseEntity<HttpStatus> deleteWeeklyActivity(@PathVariable Long id) {
        try {
            weeklyActivityService.deleteWeeklyActivity(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns 204 No Content (successful deletion)
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found
        }
    }
}
 
 
 