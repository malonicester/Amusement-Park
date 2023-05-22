package com.adventurelandVillage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.model.Admin;
import com.adventurelandVillage.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	@PostMapping("/admin/register")
	public ResponseEntity<Admin> registerAdminHandler(@RequestBody Admin admin) {
		Admin admin2 = adminService.insertAdmin(admin);
		return new ResponseEntity<Admin>(admin2, HttpStatus.CREATED);
	}

	@PatchMapping("/admin")
	public ResponseEntity<Admin> updateAdminHandler(@RequestBody Admin admin) {
		Admin admin2 = adminService.updateAdmin(admin);
		return new ResponseEntity<>(admin2, HttpStatus.CREATED);

	}

	@DeleteMapping("/admin/{adminId}")
	public ResponseEntity<Admin> deletadmin(@PathVariable Long adminId) {
		Admin admin = adminService.deleteAdmin(adminId);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}

	@GetMapping("/admin")
	public ResponseEntity<List<Admin>> getAllAdmins() {
		List<Admin> admins = adminService.getAdmins();
		return new ResponseEntity<List<Admin>>(admins, HttpStatus.OK);
	}
	
}
