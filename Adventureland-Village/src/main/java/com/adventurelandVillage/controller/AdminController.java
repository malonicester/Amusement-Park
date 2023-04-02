package com.adventurelandVillage.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.dto.CustomerActivityDTO;
import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Admin;
import com.adventurelandVillage.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	@PostMapping("/admin")
	public ResponseEntity<Admin> registerAdminHandler(@RequestBody Admin admin) {
		Admin admin2 = adminService.insertAdmin(admin);
		return new ResponseEntity<Admin>(admin2, HttpStatus.CREATED);
	}

	@PatchMapping("/admin")
	public ResponseEntity<Admin> updateAdminHandler(@RequestBody Map<String, Object> field, @RequestParam String key) {
		Admin admin2 = adminService.upAdmin(key, field);
		return new ResponseEntity<>(admin2, HttpStatus.CREATED);

	}

	@DeleteMapping("admin/{adminId}")
	public ResponseEntity<Admin> deletadmin(@PathVariable Long adminId,@RequestParam String uuid) {
		Admin admin = adminService.deleteAdmin(adminId,uuid);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}

	@GetMapping("admin/activities")
	public ResponseEntity<List<Activity>> getActivities() {
		List<Activity> activities = adminService.getListActivitiees();
		return new ResponseEntity<List<Activity>>(activities, HttpStatus.OK);
	}

	@GetMapping("admin/customers/{customerId}")
	public ResponseEntity<List<Activity>> getcustomer(@PathVariable Long customerId) {
		List<Activity> activities = adminService.getAllActivitiesByCustomer(customerId);
		return new ResponseEntity<List<Activity>>(activities, HttpStatus.OK);
	}

	@GetMapping("admin/activities/{customerId}")
	public ResponseEntity<List<Activity>> getActiviesdays(@PathVariable Long customerId,
			@RequestParam LocalDateTime fromdate, @RequestParam LocalDateTime toDate) {
		List<Activity> actvities = adminService.getAllActivitiesForDays(customerId, fromdate, toDate);
		return new ResponseEntity<List<Activity>>(actvities, HttpStatus.OK);

	}

	@GetMapping("/admin")
	public ResponseEntity<List<Admin>> getAllAdmins() {
		List<Admin> admins = adminService.getAdmins();
		return new ResponseEntity<List<Admin>>(admins, HttpStatus.OK);
	}
	@GetMapping("/admin/customerwise")
	public ResponseEntity<List<CustomerActivityDTO>> getActivitiesCustomerWiseHandler(@RequestParam String uuid){
		return new ResponseEntity<>(adminService.getActivitiesCustomerWise(uuid),HttpStatus.OK);
	}
}
