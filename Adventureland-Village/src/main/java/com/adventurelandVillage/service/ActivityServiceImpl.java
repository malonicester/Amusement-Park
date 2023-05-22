package com.adventurelandVillage.service;

import java.util.List;

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
	public Activity updateActivity(Long activityId,Activity newActivity) throws ActivityException {
		
		Activity activityToBeUpdated =  
				activityRepository.findById(activityId)
				.orElseThrow(()-> new ActivityException("No Activity Found with Id " +activityId ));
		
		if(newActivity.getCharges()!=null) {
			activityToBeUpdated.setCharges(newActivity.getCharges());
		}
		
		if(newActivity.getDescription()!=null) {
			activityToBeUpdated.setDescription(newActivity.getDescription());
		}
		return activityRepository.save(activityToBeUpdated);
	}

	@Override
	public Activity deleteActivity(Long activityId) throws ActivityException {
		Activity activity = activityRepository.findById(activityId)
				.orElseThrow(() -> new ActivityException("No Activity Found with id: "+activityId));
		activityRepository.delete(activity);
		return activity;
	}

	@Override
	public List<Activity> getActivitiesByCharges(float charges) {
		List<Activity> alist = activityRepository.findByChargesLessThan(charges);
		if (alist.isEmpty()) {
			throw new ActivityException("No activity found.");
		} else {
			return alist;
		}
	}

	@Override
	public int countActivitiesOfCharges(float charges) {
		int actCount = 0;
		if (actCount == 0) {
			throw new ActivityException("No activity found.");
		} else {
			return activityRepository.countActivitiesOfCharges(charges);

		}
	}

	@Override
	public List<Activity> getAllActivities() {
		return activityRepository.findAll();
	}

	@Override
	public Activity getActivityById(Long activityid) {
		return activityRepository.findById(activityid)
				.orElseThrow(() -> new ActivityException("No Activity Found with id " + activityid));
	}

}
