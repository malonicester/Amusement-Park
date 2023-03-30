package com.adventurelandVillage.service;

import java.util.List;

import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Admin;

public interface ActivityService {
	
	Activity addActivity(Activity activity) throws ActivityException;
	
	public Activity updateActivity(Activity activity) throws ActivityException;
	
	public String deleteActivity(Long activityId);
	
	public List<Activity> getActivitiesByCharges(float charges);
	
	int countActivitiesOfCharges(float charges);	
	
	public List<Activity> getAllActivities() ;
	
    public Activity getActivityById(Long activityId) ;
	 
	 
	
	
}
