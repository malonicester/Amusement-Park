package com.adventurelandVillage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.service.CustomerService;

import jakarta.validation.Valid;

@RestController
public class CustomerController {
	
	private CustomerService CustomerService;
	@Autowired
	public CustomerController(com.adventurelandVillage.service.CustomerService customerService) {
		super();
		CustomerService = customerService;
	}
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> insertCustomerHandler(@Valid @RequestBody Customer customer){
		Customer cust=CustomerService.insertCustomer(customer);
		return new ResponseEntity<Customer>(cust,HttpStatus.CREATED);
	}
	
	
	
	
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody Customer customer){
		Customer cust=CustomerService.insertCustomer(customer);
		return new ResponseEntity<Customer>(cust,HttpStatus.CREATED);
	}
}
