package com.adventurelandVillage.adminservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.adminrepository.ActivityRepo;
import com.adventurelandVillage.adminrepository.AdminRepo;
import com.adventurelandVillage.adminrepository.SessionRepo;
import com.adventurelandVillage.exceptions.ActivityException;
import com.adventurelandVillage.exceptions.AdminException;
import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Admin;
import com.adventurelandVillage.model.CurrentUserSession;
import com.adventurelandVillage.model.Customer;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private ActivityRepo activityRepo;
	@Autowired
	private SessionRepo sessionRepo;

	@Override
	public Admin insertAdmin(Admin admin) {
		Admin admin2 = adminRepo.findByUserName(admin.getUserName());
		// TODO Auto-generated method stub
		if (admin2 != null) {
			throw new AdminException("admin already registered with this username");
		}
		return adminRepo.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		Admin admin2 = adminRepo.findById(admin.getAdminId()).orElseThrow(() -> new AdminException("not found"));
		if (admin2 == null) {
			throw new AdminException();
		}
		return adminRepo.save(admin2);
	}

	@Override
	public Admin deleteAdmin(Long adminId) {
		Admin admin = adminRepo.findById(adminId).orElseThrow(() -> new AdminException("not found"));

		adminRepo.delete(admin);

		return admin;
	}

	@Override
	public List<Activity> getAllActivities(Long customerId) {
		// TODO Auto-generated method stub
		List<Activity> activities = activityRepo.findByCustomerId(customerId);
		if (activities.isEmpty()) {
			throw new ActivityException();
		}
		return activities;
	}

	@Override
	public List<Activity> getListActivitiees() {
		// TODO Auto-generated method stub
		List<Activity> activities = activityRepo.findAll();
		if (activities.isEmpty()) {
			throw new ActivityException();
		}
		return activities;
	}

	@Override
	public List<Activity> getActivitiesCustomerWise() {
		// TODO Auto-generated method stub
		List<Activity> activities = activityRepo.getActivitiesCustomer();
		if (activities.isEmpty()) {
			throw new ActivityException();
		}
		return activities;
	}

	@Override
	public List<Activity> getActivitiesDateWise() {
		List<Activity> activities = activityRepo.getActivitiesDate();
		if (activities.isEmpty()) {
			throw new ActivityException();
		}
		return activities;
	}

	@Override
	public List<Activity> getAllActivitiesForDays(Long customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		List<Activity> activities=activityRepo.getActivityByCustomerDatebetween(customerId, fromDate, toDate);
		// TODO Auto-generated method stub
		if(activities.isEmpty()) {
			throw new ActivityException();
		}
		return activities;
	}

	@Override
	public Admin upAdmin(Admin admin, String key) {
		// TODO Auto-generated method stub
		CurrentUserSession loggedInUser = sessionRepo.findByUuuid(key);
		if (loggedInUser == null) {
			throw new AdminException("please provide a valid key to update a admin");

		}
		if (admin.getAdminId().equals(loggedInUser.getUserId())) {
			// If loggedinUser id is same as the id of supplied admin which we want to
			// update
			return adminRepo.save(admin);
		} else {
			throw new AdminException("Invalid Admin details,please login first");
		}

	}

}
