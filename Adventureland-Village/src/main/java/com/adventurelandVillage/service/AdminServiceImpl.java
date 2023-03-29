package com.adventurelandVillage.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.exception.AdminException;
import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Admin;
import com.adventurelandVillage.model.CurrentUserSession;
import com.adventurelandVillage.repository.ActivityRepository;
import com.adventurelandVillage.repository.AdminRepo;
import com.adventurelandVillage.repository.SessionRepo;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private ActivityRepository activityRepo;
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
		List<Activity> activities = activityRepo.getCustomerId(customerId);
		if (activities.isEmpty()) {
			throw new ActivityException();
		}
		return null;
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
		List<Activity> activities = activityRepo.getCustomerWise();
		if (activities.isEmpty()) {
			throw new ActivityException();
		}
		return activities;
	}

	@Override
	public List<Activity> getActivitiesDateWise() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<Activity> getAllActivitiesForDays(Long customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		// TODO Auto-generated method stub
		List<Activity> activities = activityRepo.getDateBetween(customerId, fromDate, toDate);
		if (activities.isEmpty()) {
			throw new ActivityException();
		}
		return activities;
	}

	@Override
	public Admin upAdmin(Admin admin, String key) {
		// TODO Auto-generated method stub
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		if (loggedInUser == null) {
			throw new AdminException("please provide a valid key to update a admin");

		}
		if (admin.getAdminId()==loggedInUser.getUserId()) {
			// If loggedinUser id is same as the id of supplied admin which we want to
			// update
			return adminRepo.save(admin);
		} else {
			throw new AdminException("Invalid Admin details,please login first");
		}

	}
	
	public List<Admin> getAdmins(){
		List<Admin> admins=adminRepo.findAll();
		if(admins.isEmpty()) {
			throw new AdminException("admin not there");
		}
		return admins;
	}

}
