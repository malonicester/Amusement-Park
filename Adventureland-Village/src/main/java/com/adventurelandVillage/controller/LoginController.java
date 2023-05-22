package com.adventurelandVillage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.model.Admin;
import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.repository.AdminRepo;
import com.adventurelandVillage.repository.CustomerRepository;

@RestController
public class LoginController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AdminRepo adminRepository;
	
	@GetMapping("/customers/signIn")
	public ResponseEntity<Customer> customerLoginHandler(Authentication authentication){
		Customer customer = customerRepository.findByEmail(authentication.getName())
				.orElseThrow(()->new UsernameNotFoundException("Incorrect userName or password"));
		return new ResponseEntity<Customer>(customer,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/admin/signIn")
	public ResponseEntity<Admin> adminLoginHandler(Authentication authentication){
		Admin admin = adminRepository.findByEmail(authentication.getName())
				.orElseThrow(()-> new UsernameNotFoundException("Incorrect userName or password"));
		
		return new ResponseEntity<Admin>(admin,HttpStatus.ACCEPTED);
	}
	
}
