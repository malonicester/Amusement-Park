package com.adventurelandVillage.service;

import java.util.List;

import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.model.Activity;

public interface ActivityService {
	
	Activity addActivity(Activity activity) throws ActivityException;
	
	public Activity updateActivity(Activity activity) throws ActivityException;
	
	public String deleteActivity(Long activityId);
	
	public List<Activity> getActivitiesByCharges(float charges);
	
	int countActivitiesOfCharges(float charges);	
	
	public List<Activity> getAllActivities() ;
	
    public Activity getActivityById(Long activityId) ;

	 
	 public Activity updateActivity(Activity activity);
	 
	 public Activity deleteActivity(Long activityId) throws ActivityException;

	 
	 
	
	
}
