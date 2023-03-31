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
	public List<Activity> getAllActivities() {
		return activityRepository.findAll();
	}

	@Override
	public List<Activity> getActivitiesByCharges(float charges) {
		return activityRepository.findByChargesLessThan(charges);
	}

	public Activity getActivityById(Long Activityid) {
		return activityRepository.findById(Activityid).orElse(null);
	}

	@Override
	public Activity updateActivity(Activity activity) {
		return activityRepository.save(activity);
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

	public Activity getActivityById(long activityId) {
		return null;
	}

}
