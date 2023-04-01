package com.adventurelandVillage.service;

import java.util.List;

import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.model.Activity;

public interface ActivityService {

	Activity addActivity(Activity activity,String uuid);

	public Activity updateActivity(Activity activity,String uuid) throws ActivityException;
	
	public Activity deleteActivity(Long activityId, String uuid) throws ActivityException;
	
	public List<Activity> getActivitiesByCharges(float charges);

	public int countActivitiesOfCharges(float charges);

	//-=-=-==-=-=-=-====-==-Extra=-=-===-=======-=//
	
	public List<Activity> getAllActivities();
	
	public Activity getActivityById(Long Activityid);

}
