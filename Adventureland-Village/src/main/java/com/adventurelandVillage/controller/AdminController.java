package com.adventurelandVillage.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Admin;
import com.adventurelandVillage.service.AdminService;

@RestController
public class AdminController {
	@Autowired
private AdminService adminService;
	
	@PostMapping("/insertadmin")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin){
		Admin admin2=adminService.insertAdmin(admin);
		return new ResponseEntity<Admin>(admin2,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateadmin")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin){
		Admin ad=adminService.updateAdmin(admin);
		return new ResponseEntity<Admin>(ad,HttpStatus.OK);
	}
	
@PutMapping("/updateadminbykey")
public ResponseEntity<Admin> upadmin(@RequestBody Admin admin,@RequestParam  String key){
	Admin admin2=adminService.upAdmin(admin, key);
	return new ResponseEntity<>(admin2,HttpStatus.CREATED);
	
}

@DeleteMapping("/deletecustomers/{adminId}")
public ResponseEntity<Admin> deletadmin(@PathVariable Long adminId){
	Admin admin=adminService.deleteAdmin(adminId);
	return new ResponseEntity<Admin>(admin,HttpStatus.OK);
}

@GetMapping("/getactivities")
public ResponseEntity<List<Activity>> getActivities(){
	List<Activity> activities=adminService.getListActivitiees();
	return new ResponseEntity<List<Activity>>(activities,HttpStatus.OK);
}

@GetMapping("/getbycustomer/{customerId}")
public ResponseEntity<List<Activity>> getcustomer(@PathVariable Long customerId){
	List<Activity> activities=adminService.getAllActivities(customerId);
	return new ResponseEntity<List<Activity>>(activities,HttpStatus.OK);
}

@GetMapping("/getActivitesbydays/{customerId}")
public ResponseEntity<List<Activity>> getActiviesdays(@PathVariable Long customerId,@RequestParam LocalDateTime fromdate,@RequestParam LocalDateTime toDate){
	List<Activity> actvities=adminService.getAllActivitiesForDays(customerId, fromdate, toDate);
	return new ResponseEntity<List<Activity>>(actvities,HttpStatus.OK);
	
	
}

@GetMapping("/getadmins")
public ResponseEntity<List<Admin>> getAllAdmins(){
	List<Admin> admins=adminService.getAdmins();
	return new ResponseEntity<List<Admin>>(admins,HttpStatus.OK);
			
}

}
