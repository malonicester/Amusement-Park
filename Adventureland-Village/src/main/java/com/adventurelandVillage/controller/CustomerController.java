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
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.model.LoginDTO;
import com.adventurelandVillage.service.CustomerService;

import jakarta.validation.Valid;

@RestController
public class CustomerController {

	private CustomerService CustomerService;

	@Autowired
	public CustomerController(com.adventurelandVillage.service.CustomerService customerService) {
	
		CustomerService = customerService;
	}

	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> insertCustomerHandler(@Valid @RequestBody Customer customer) {
		Customer cust = CustomerService.insertCustomer(customer);
		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);
	}

	
	@PutMapping("/updateCustomer/{key}")
	public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody Customer customer,
			@Valid @PathVariable("key")String key){
		Customer cust=CustomerService.updateCustomer(customer, key);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCustomer/{customerId}/{key}")
	public ResponseEntity<String> deleteCustomerHandler(@Valid @PathVariable("customerId") Long customerId,
			@Valid @PathVariable("key")String key){
		String resp=CustomerService.deleteCustomer(customerId,key);
		return new ResponseEntity<String>(resp,HttpStatus.OK);
	}
	
	@GetMapping("/allCustomers/{key}")
	public ResponseEntity<List<Customer>> viewCustomersHandler(@Valid @PathVariable("key")String key){
		List<Customer> custs=CustomerService.viewCustomers(key);
		return new ResponseEntity<List<Customer>>(custs,HttpStatus.OK);
	}
	
	@GetMapping("/customerById/{customerId}/{key}")
	public ResponseEntity<Customer> viewCustomerHandler(@Valid @PathVariable("customerId") Long customerId,
			@Valid @PathVariable("key")String key){
		Customer cust=CustomerService.viewCustomer(customerId,key);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}
	
	@GetMapping("/validateCustomer/{key}")
	public ResponseEntity<Customer> validateCustomerHandler(@Valid @PathVariable("key")String key,
			@Valid LoginDTO loginDto){
		Customer cust=CustomerService.validateCustomer(key, loginDto);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);



	}

}
