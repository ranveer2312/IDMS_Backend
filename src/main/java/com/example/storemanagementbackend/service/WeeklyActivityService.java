package com.example.storemanagementbackend.service;
 
import com.example.storemanagementbackend.model.WeeklyActivity;
 
import java.util.List;
 
/**
 * Service interface for managing WeeklyActivity entities.
 * Defines the business operations available for weekly activities.
 */
public interface WeeklyActivityService {
 
 
    WeeklyActivity createWeeklyActivity(WeeklyActivity weeklyActivity);
 
 
    WeeklyActivity getWeeklyActivityById(Long id);
 
 
    List<WeeklyActivity> getAllWeeklyActivities();
 
 
    List<WeeklyActivity> searchWeeklyActivities(String searchTerm);
 
 
    List<WeeklyActivity> getWeeklyActivitiesByCategory(String category);
 
 
    List<WeeklyActivity> getWeeklyActivitiesByStatus(String status);
 
 
 
    WeeklyActivity updateWeeklyActivity(Long id, WeeklyActivity weeklyActivityDetails);
 
    /**
     * Deletes a weekly activity by its ID.
     * @param id The ID of the weekly activity to delete.
//     * @throws NoSuchElementException if the weekly activity with the given ID does not exist.
     */
    void deleteWeeklyActivity(Long id);
}
 
 
 