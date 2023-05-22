package com.adventurelandVillage.service;

import java.time.LocalDateTime;
import java.util.List;

import com.adventurelandVillage.dto.CustomerActivityDTO;
import com.adventurelandVillage.exception.AdminException;
import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Admin;

public interface AdminService {
	
	public Admin insertAdmin(Admin admin) throws AdminException;

	public Admin updateAdmin(Admin admin) throws AdminException;
	
	public Admin deleteAdmin(Long adminId);

	public List<Activity> getAllActivitiesByCustomer(Long customerId);

	public List<Activity> getListActivitiees();

	public List<CustomerActivityDTO> getActivitiesCustomerWise();

	public List<Activity> getActivitiesDateWise();

	public List<Activity> getAllActivitiesForDays(Long customerId, LocalDateTime fromDate, LocalDateTime toDate);

	public List<Admin> getAdmins() throws AdminException;
}
