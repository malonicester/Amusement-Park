package com.adventurelandVillage.service;

import java.util.List;

import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.model.Activity;

public interface ActivityService {

	public Activity addActivity(Activity activity);

	public Activity updateActivity(Long activityId,Activity activity) throws ActivityException;
	
	public Activity deleteActivity(Long activityId) throws ActivityException;
	
	public List<Activity> getActivitiesByCharges(float charges);

	public int countActivitiesOfCharges(float charges);
	
	public List<Activity> getAllActivities();
	
	public Activity getActivityById(Long Activityid);

}
