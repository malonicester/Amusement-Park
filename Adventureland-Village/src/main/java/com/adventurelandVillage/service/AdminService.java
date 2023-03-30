package com.adventurelandVillage.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.adventurelandVillage.exception.AdminException;
import com.adventurelandVillage.exception.LoginException;
import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Admin;

public interface AdminService {
	public Admin insertAdmin(Admin admin) throws AdminException;

	public Admin updateAdmin(Admin admin);

	public Admin upAdmin(String key, Map<String, Object> fields) throws LoginException, AdminException;

	public Admin deleteAdmin(Long adminId);

	public List<Activity> getAllActivities(Long customerId);

	public List<Activity> getListActivitiees();

	public List<Activity> getActivitiesCustomerWise();

	public List<Activity> getActivitiesDateWise();

	public List<Activity> getAllActivitiesForDays(Long customerId, LocalDateTime fromDate, LocalDateTime toDate);

	public List<Admin> getAdmins() throws AdminException;
}
