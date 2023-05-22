package com.adventurelandVillage.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.dto.CustomerActivityDTO;
import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.exception.AdminException;
import com.adventurelandVillage.exception.LoginException;
import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Admin;
import com.adventurelandVillage.model.Role;
import com.adventurelandVillage.repository.ActivityRepository;
import com.adventurelandVillage.repository.AdminRepo;
import com.adventurelandVillage.repository.CustomerRepository;
import com.adventurelandVillage.repository.TicketRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepo adminRepository;
	@Autowired
	private ActivityRepository activityRepo;
	

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private CustomerRepository customerRepository;

	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Admin insertAdmin(Admin admin) throws AdminException {
		if (admin == null)
			throw new AdminException("Please Enter Valid Details");
		admin.setRole(Role.ROLE_ADMIN);
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return adminRepository.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin updatedAdmin) throws AdminException {
		
		Authentication authentication = SecurityContextHolder
				.getContext()
				.getAuthentication();
		
		Admin admin = adminRepository.findByEmail(authentication.getName())
				.orElseThrow(()-> new LoginException("User Not logged in"));
		
		if(updatedAdmin.getMobileNumber()!=null) {
		    admin.setMobileNumber(updatedAdmin.getMobileNumber());
		}
		
		if(updatedAdmin.getAddress()!=null) {
			admin.setAddress(updatedAdmin.getAddress());
		}
		return adminRepository.save(admin);
	}

	@Override
	public Admin deleteAdmin(Long adminId) throws AdminException {
		Admin admin = adminRepository.findById(adminId)
				.orElseThrow(() -> new AdminException("not found"));
		adminRepository.delete(admin);
		return admin;
	}

	@Override
	public List<Activity> getAllActivitiesByCustomer(Long customerId) {
		List<Activity> activities = ticketRepository.getActivityByCustomer(customerId);
		if (activities.isEmpty()) {
			throw new ActivityException("Activity not found");
		}
		return activities;
	}

	@Override
	public List<Activity> getListActivitiees() {
		List<Activity> activities = activityRepo.findAll();
		if (activities.isEmpty()) {
			throw new ActivityException("Activity not found");
		}
		return activities;
	}

	@Override
	public List<CustomerActivityDTO> getActivitiesCustomerWise() {	
		List<CustomerActivityDTO> customerActivityDTO = customerRepository.findAll().stream()
				.map((e) -> new CustomerActivityDTO(e.getCustomerId(), e.getUserName(),
						ticketRepository.getActivityByCustomer(e.getCustomerId()), (float) 0))
				.collect(Collectors.toList()).stream().filter((e) -> !e.getActivities().isEmpty())
				.collect(Collectors.toList());
		return customerActivityDTO;
	}

	@Override
	public List<Activity> getActivitiesDateWise() {
		return null;
	}

	@Override
	public List<Activity> getAllActivitiesForDays(Long customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		List<Activity> activities = activityRepo.getDateBetween(customerId, fromDate, toDate);
		if (activities.isEmpty()) {
			throw new ActivityException("Activity not found");
		}
		return activities;
	}



	public List<Admin> getAdmins() throws AdminException {
		List<Admin> admins = adminRepository.findAll();
		if (admins.isEmpty()) {
			throw new AdminException("admin not there");
		}
		return admins;
	}

}
