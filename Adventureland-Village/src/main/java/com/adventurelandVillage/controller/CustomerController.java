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
import com.adventurelandVillage.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customers/register")
	public ResponseEntity<Customer> registerCustomerHandler(@RequestBody Customer customer) {
		return new ResponseEntity<Customer>(customerService.insertCustomer(customer), HttpStatus.CREATED);
	}

	@PutMapping("/customers")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
		return new ResponseEntity<Customer>(
				customerService.updateCustomer(customer),
				HttpStatus.OK
				);
	}
	
	@DeleteMapping("/customers")
	public ResponseEntity<Customer> deleteCustomer(Long customerId){
		return new ResponseEntity<Customer>(
				customerService.deleteCustomer(customerId),
				HttpStatus.OK
				);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomersHandler(){
		return new ResponseEntity<List<Customer>>(
				customerService.getAllCustomers(),
				HttpStatus.OK
				);
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable Long customerId){
		return new ResponseEntity<Customer>(
				customerService.findCustomerById(customerId),
				HttpStatus.OK
				);
	}
}
