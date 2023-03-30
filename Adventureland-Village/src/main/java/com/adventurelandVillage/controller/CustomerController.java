package com.adventurelandVillage.controller;


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
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.model.LoginDTO;
import com.adventurelandVillage.service.CustomerService;
import com.adventurelandVillage.service.LoginService;

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
	public ResponseEntity<Customer> insertCustomerHandler(@Valid @RequestBody Customer customer) {
		Customer cust = CustomerService.insertCustomer(customer);
		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);
	}

	
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody Customer customer){
		Customer cust=CustomerService.insertCustomer(customer);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}
	
	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<String> deleteCustomerHandler(@Valid @PathVariable("customerId") Long customerId){
		String resp=CustomerService.deleteCustomer(customerId);
		return new ResponseEntity<String>(resp,HttpStatus.OK);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewCustomersHandler(){
		List<Customer> custs=CustomerService.viewCustomers();
		return new ResponseEntity<List<Customer>>(custs,HttpStatus.OK);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> viewCustomerHandler(@Valid @PathVariable("customerId") Long customerId){
		Customer cust=CustomerService.viewCustomer(customerId);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}
	
	@GetMapping("/customer/{username}/{password}")
	public ResponseEntity<Customer> validateCustomerHandler(@Valid @PathVariable("username") String username,
			@Valid @PathVariable("password") String password){
		Customer cust=CustomerService.validateCustomer(username, password);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);


	}	
}
