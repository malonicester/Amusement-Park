package com.adventurelandVillage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService{
	
	
	 @Autowired
	private ActivityRepository activityRepository;
	@Override
	public Activity addActivity(Activity activity) {
		// TODO Auto-generated method stub
		return activityRepository.save(activity);
	}

	@Override
	public List<Activity> getAllActivities() {
		// TODO Auto-generated method stub
		return activityRepository.findAll();
	}

	@Override
	public List<Activity> getActivitiesByCharges(float charges) {
		// TODO Auto-generated method stub
		  return activityRepository.findByChargesLessThan(charges);
	}

	public Activity getActivityById(Long Activityid) {
		// TODO Auto-generated method stub
		  return activityRepository.findById( Activityid).orElse(null);
	}

	@Override
	public Activity updateActivity(Activity activity) {
		// TODO Auto-generated method stub
		 return activityRepository.save(activity);
	}

	public void deleteActivity(Long activityId) {
		// TODO Auto-generated method stub
		activityRepository.deleteById(activityId);
		
	}

	public Activity getActivityById(long activityId) {
		// TODO Auto-generated method stub
		return null;
	}

}
