package com.adventurelandVillage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.service.ActivityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/activities")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	// Create activity
	@PostMapping("")
	public Activity createActivity(@Valid @RequestBody Activity activity,@RequestParam String uuid) {
		return activityService.addActivity(activity,uuid);
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
	@PatchMapping("/{id}")
	public ResponseEntity<Activity> updateActivity(@PathVariable(value = "id") Long activityId,
			@Valid @RequestBody Map<String,Object> fields,@RequestParam String uuid) throws ConfigDataResourceNotFoundException {
		Activity updatedActivity = activityService.updateActivity(activityId,fields,uuid);
		return ResponseEntity.ok(updatedActivity);
	}

	// Delete activity
	@DeleteMapping("/{id}")
	public ResponseEntity<Activity> deleteActivity(@PathVariable(value = "id") Long activityId,@RequestParam String uuid) {
		return new ResponseEntity<Activity>(activityService.deleteActivity(activityId,uuid), HttpStatus.OK);
	}

}
