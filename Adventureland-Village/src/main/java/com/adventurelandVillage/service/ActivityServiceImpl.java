package com.adventurelandVillage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public Activity addActivity(Activity activity) {
		return activityRepository.save(activity);
	}	
	@Override
	public Activity updateActivity(Activity activity) throws ActivityException{
		Optional<Activity> actvty=activityRepository.findById(activity.getActivityId());
		if(actvty.isPresent()) {
			return activityRepository.save(activity);			
		}else {
			throw new ActivityException("No any Activity found by activityId : "+activity.getActivityId());
		}

	}	
	@Override
	public List<Activity> getActivitiesByCharges(float charges) {
		List<Activity> alist=activityRepository.findByChargesLessThan(charges);
		if(alist.isEmpty()) {
			throw new ActivityException("No activity found.");
		}else {	
			return alist;
		}
	}
	@Override
	public Activity getActivityById(Long Activityid) {
		return activityRepository.findById(Activityid).orElseThrow(()->new ActivityException("No Activity Foundw with id " + Activityid));
	}
	@Override
	public List<Activity> getAllActivities() {
		// TODO Auto-generated method stub
		return activityRepository.findAll();
	}
	public Activity deleteActivity(Long activityId) throws ActivityException {
		Optional<Activity> optional = activityRepository.findById(activityId);
		if (optional.isPresent()) {
			Activity activity = optional.get();
			activityRepository.delete(activity);
			return activity;
		}
		throw new ActivityException("No Activity Found with id " + activityId);
	}

	@Override
	public int countActivitiesOfCharges(float charges) {
		int actCount=0;		  
	  	if(actCount==0) {
			throw new ActivityException("No activity found.");
		}else {	
			return activityRepository.countActivitiesOfCharges(charges);
		
		}
	}

}
