package com.adventurelandVillage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.service.ActivityService;

@RestController
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@PostMapping("/activities")
    public ResponseEntity<Activity> addActivityHandler(@RequestBody Activity activity){
    	return new ResponseEntity<Activity>(
    			activityService.addActivity(activity),
    			HttpStatus.CREATED
    			);
    }
	
	@GetMapping("/activities")
	public ResponseEntity<List<Activity>> getAllActivitiesHandler(){
		return new ResponseEntity<List<Activity>>(
				activityService.getAllActivities(),
				HttpStatus.OK
				);
	}
}
