package com.adventurelandVillage.admincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.adminservice.AdminService;
import com.adventurelandVillage.model.Admin;
import com.adventurelandVillage.model.Customer;

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
	
	


}
