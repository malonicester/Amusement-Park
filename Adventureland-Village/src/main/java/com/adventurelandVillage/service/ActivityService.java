package com.adventurelandVillage.service;

import java.util.List;

import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.model.Activity;

public interface ActivityService {

	Activity addActivity(Activity activity);

	public List<Activity> getAllActivities();

	public List<Activity> getActivitiesByCharges(float charges);

	public Activity getActivityById(Long Activityid);

	public Activity updateActivity(Activity activity);

	public Activity deleteActivity(Long activityId) throws ActivityException;

	public int countActivitiesOfCharges(float charges);

}
