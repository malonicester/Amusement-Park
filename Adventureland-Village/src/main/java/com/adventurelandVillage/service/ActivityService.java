package com.adventurelandVillage.service;

import java.util.List;
import java.util.Map;

import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.model.Activity;

public interface ActivityService {

	Activity addActivity(Activity activity,String uuid);

	public Activity updateActivity(Long activityId,Map<String,Object> fields,String uuid) throws ActivityException;
	
	public Activity deleteActivity(Long activityId, String uuid) throws ActivityException;
	
	public List<Activity> getActivitiesByCharges(float charges);

	public int countActivitiesOfCharges(float charges);

	//-=-=-==-=-=-=-====-==-Extra=-=-===-=======-=//
	
	public List<Activity> getAllActivities();
	
	public Activity getActivityById(Long Activityid);

}
