package com.adventurelandVillage.service;

import java.util.List;

import com.adventurelandVillage.model.Activity;

public interface ActivityService {
	
	Activity addActivity(Activity activity);
	
	public List<Activity> getAllActivities() ;
	
	public List<Activity> getActivitiesByCharges(float charges);
	
	 public Activity getActivityById(long activityId) ;
	 
	 public Activity updateActivity(Activity activity);
	 
	 public void deleteActivity(Long activityId);
	
	
}
