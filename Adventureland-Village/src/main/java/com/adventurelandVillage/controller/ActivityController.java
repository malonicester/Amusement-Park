package com.adventurelandVillage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.repository.ActivityRepository;
import com.adventurelandVillage.service.ActivityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    
    @GetMapping("/")
    public String home() {
        return "Welcome to the activity application!";
    }
    
    // Create activity
    @PostMapping("")
    public Activity createActivity(@Valid @RequestBody Activity activity) {
    	return activityService.addActivity(activity);
    }

    // Get all activities
    @GetMapping("")
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    // Get activities by charges
    @GetMapping("/bycharges/{charges}")
    public List<Activity> getActivitiesByCharges(@PathVariable(value = "charges") float charges) {
    	return activityService.getActivitiesByCharges(charges);
    }

    // Get activity by ID
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable(value = "id") Long activityId)
            throws ConfigDataResourceNotFoundException {
        Activity activity = activityService.getActivityById(activityId);
        return ResponseEntity.ok().body(activity);
    }


    // Update activity
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable(value = "id") int activityId,
            @Valid @RequestBody Activity activityDetails) throws ConfigDataResourceNotFoundException {
        Activity updatedActivity = activityService.updateActivity(activityDetails);
        return ResponseEntity.ok(updatedActivity);
    }

    // Delete activity
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteActivity(@PathVariable(value = "id") Long activityId)
            throws ConfigDataResourceNotFoundException {
        activityService.deleteActivity(activityId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
