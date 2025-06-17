 
 
package com.example.storemanagementbackend.service.impl;
 
import com.example.storemanagementbackend.model.WeeklyActivity;
import com.example.storemanagementbackend.repository.WeeklyActivityRepository;
import com.example.storemanagementbackend.service.WeeklyActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
 
/**
 * Implementation of the WeeklyActivityService interface.
 * Contains the business logic for managing weekly activities.
 */
@Service // Marks this class as a Spring service component
public class WeeklyActivityServiceImpl implements WeeklyActivityService {
 
    private final WeeklyActivityRepository weeklyActivityRepository;
 
    @Autowired // Injects the WeeklyActivityRepository dependency
    public WeeklyActivityServiceImpl(WeeklyActivityRepository weeklyActivityRepository) {
        this.weeklyActivityRepository = weeklyActivityRepository;
    }
 
    @Override
    @Transactional // Ensures the entire method runs as a single transaction
    public WeeklyActivity createWeeklyActivity(WeeklyActivity weeklyActivity) {
        // You might add validation logic here (e.g., ensure title is not null/empty)
        if (weeklyActivity.getTitle() == null || weeklyActivity.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Activity title cannot be empty.");
        }
        if (weeklyActivity.getActivityDate() == null) {
            throw new IllegalArgumentException("Activity date cannot be empty.");
        }
        // Set default status if not provided (as per UI, "Pending" is common for new activities)
        if (weeklyActivity.getStatus() == null || weeklyActivity.getStatus().isEmpty()) {
            weeklyActivity.setStatus("Pending");
        }
        return weeklyActivityRepository.save(weeklyActivity);
    }
 
    @Override
    public WeeklyActivity getWeeklyActivityById(Long id) {
        return weeklyActivityRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Weekly Activity not found with id: " + id));
    }
 
    @Override
    public List<WeeklyActivity> getAllWeeklyActivities() {
        return weeklyActivityRepository.findAll();
    }
 
    @Override
    public List<WeeklyActivity> searchWeeklyActivities(String searchTerm) {
        // This is a simple in-memory search based on title or description.
        // For more efficient or complex search (e.g., full-text search),
        // consider using Spring Data JPA's derived queries, @Query, or a dedicated search solution.
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return weeklyActivityRepository.findAll();
        }
        String lowerCaseSearchTerm = searchTerm.toLowerCase();
        return weeklyActivityRepository.findAll().stream()
                .filter(activity -> activity.getTitle().toLowerCase().contains(lowerCaseSearchTerm) ||
                        (activity.getDescription() != null && activity.getDescription().toLowerCase().contains(lowerCaseSearchTerm)))
                .collect(Collectors.toList());
    }
 
    @Override
    public List<WeeklyActivity> getWeeklyActivitiesByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            return weeklyActivityRepository.findAll(); // Or throw an error, depending on requirements
        }
        return weeklyActivityRepository.findByCategory(category);
    }
 
    @Override
    public List<WeeklyActivity> getWeeklyActivitiesByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            return weeklyActivityRepository.findAll(); // Or throw an error
        }
        return weeklyActivityRepository.findByStatus(status);
    }
 
    @Override
    @Transactional
    public WeeklyActivity updateWeeklyActivity(Long id, WeeklyActivity weeklyActivityDetails) {
        WeeklyActivity existingActivity = weeklyActivityRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Weekly Activity not found with id: " + id));
 
        // Update fields based on the provided details.
        // Null checks ensure we only update fields that are explicitly provided in the request.
        if (weeklyActivityDetails.getTitle() != null) {
            existingActivity.setTitle(weeklyActivityDetails.getTitle());
        }
        if (weeklyActivityDetails.getCategory() != null) {
            existingActivity.setCategory(weeklyActivityDetails.getCategory());
        }
        if (weeklyActivityDetails.getActivityDate() != null) {
            existingActivity.setActivityDate(weeklyActivityDetails.getActivityDate());
        }
        if (weeklyActivityDetails.getActivityTime() != null) {
            existingActivity.setActivityTime(weeklyActivityDetails.getActivityTime());
        }
        if (weeklyActivityDetails.getAssignedTo() != null) {
            existingActivity.setAssignedTo(weeklyActivityDetails.getAssignedTo());
        }
        if (weeklyActivityDetails.getPriority() != null) {
            existingActivity.setPriority(weeklyActivityDetails.getPriority());
        }
        if (weeklyActivityDetails.getStatus() != null) {
            existingActivity.setStatus(weeklyActivityDetails.getStatus());
        }
        if (weeklyActivityDetails.getDescription() != null) {
            existingActivity.setDescription(weeklyActivityDetails.getDescription());
        }
        if (weeklyActivityDetails.getNotes() != null) {
            existingActivity.setNotes(weeklyActivityDetails.getNotes());
        }
 
        return weeklyActivityRepository.save(existingActivity);
    }
 
    @Override
    @Transactional
    public void deleteWeeklyActivity(Long id) {
        if (!weeklyActivityRepository.existsById(id)) {
            throw new NoSuchElementException("Weekly Activity not found with id: " + id);
        }
        weeklyActivityRepository.deleteById(id);
    }
}
 
 
 
 