package com.adventurelandVillage.service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.exception.AdminException;
import com.adventurelandVillage.exception.LoginException;
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
	public Admin insertAdmin(Admin admin) throws AdminException {
		Admin admin2 = adminRepo.findByUserName(admin.getUserName());
		if (admin2 != null) {
			throw new AdminException("admin already registered with this username");
		}
		return adminRepo.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin) throws AdminException {
		Admin admin2 = adminRepo.findById(admin.getAdminId()).orElseThrow(() -> new AdminException("not found"));
		if (admin2 == null) {
			throw new AdminException();
		}
		return adminRepo.save(admin2);
	}

	@Override
	public Admin deleteAdmin(Long adminId) throws AdminException {
		Admin admin = adminRepo.findById(adminId).orElseThrow(() -> new AdminException("not found"));
		adminRepo.delete(admin);
		return admin;
	}

	@Override
	public List<Activity> getAllActivities(Long customerId) {
		List<Activity> activities = activityRepo.getCustomerId(customerId);
		if (activities.isEmpty()) {
			throw new ActivityException();
		}
		return null;
	}

	@Override
	public List<Activity> getListActivitiees() {
		List<Activity> activities = activityRepo.findAll();
		if (activities.isEmpty()) {
			throw new ActivityException();
		}
		return activities;
	}

	@Override
	public List<Activity> getActivitiesCustomerWise() {
		List<Activity> activities = activityRepo.getCustomerWise();
		if (activities.isEmpty()) {
			throw new ActivityException();
		}
		return activities;
	}

	@Override
	public List<Activity> getActivitiesDateWise() {
		return null;
	}

	@Override
	public List<Activity> getAllActivitiesForDays(Long customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		List<Activity> activities = activityRepo.getDateBetween(customerId, fromDate, toDate);
		if (activities.isEmpty()) {
			throw new ActivityException();
		}
		return activities;
	}

	@Override
	public Admin upAdmin(String uuid, Map<String, Object> fields) throws LoginException, AdminException {

		CurrentUserSession currentUserSession = sessionRepo.findByUuid(uuid);
		if (currentUserSession == null)
			throw new LoginException("Please Login First");

		Optional<Admin> optional = adminRepo.findById(currentUserSession.getUserId());
		if (optional.isPresent()) {

			Admin admin = optional.get();

			fields.forEach((key, value) -> {

				Field field = ReflectionUtils.findField(Admin.class, key);

				field.setAccessible(true);

				ReflectionUtils.setField(field, admin, value);
			});
			return adminRepo.save(admin);
		}
		throw new AdminException("No Admin Found");
	}

	public List<Admin> getAdmins() throws AdminException {
		List<Admin> admins = adminRepo.findAll();
		if (admins.isEmpty()) {
			throw new AdminException("admin not there");
		}
		return admins;
	}

}
