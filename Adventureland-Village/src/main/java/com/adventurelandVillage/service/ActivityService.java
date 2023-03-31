package com.adventurelandVillage.service;

import java.util.List;

import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Admin;

public interface ActivityService {
	
	Activity addActivity(Activity activity);
	
	public List<Activity> getAllActivities() ;
	
	public List<Activity> getActivitiesByCharges(float charges);
	
	 public Activity getActivityById(long activityId) ;
	 
	 public Activity updateActivity(Activity activity);
	 
	 public Activity deleteActivity(Long activityId) throws ActivityException;
	 
	 
	
	
}
