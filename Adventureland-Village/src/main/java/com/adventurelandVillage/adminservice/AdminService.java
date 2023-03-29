package com.adventurelandVillage.adminservice;

import java.time.LocalDateTime;
import java.util.List;

import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Admin;


public interface AdminService {
	public Admin insertAdmin(Admin admin);

	public Admin updateAdmin(Admin admin);
	
	public Admin upAdmin(Admin admin,String key);

	public Admin deleteAdmin(Long adminId);

	public List<Activity> getAllActivities(Long customerId);

	public List<Activity> getListActivitiees();

	public List<Activity> getActivitiesCustomerWise();

	public List<Activity> getActivitiesDateWise();

	public List<Activity> getAllActivitiesForDays(Long customerId, LocalDateTime fromDate, LocalDateTime toDate);
}
