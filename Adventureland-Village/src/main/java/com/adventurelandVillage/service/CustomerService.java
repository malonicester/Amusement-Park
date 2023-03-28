package com.adventurelandVillage.service;

import java.util.List;

import com.adventurelandVillage.exception.CustomerException;
import com.adventurelandVillage.model.Customer;


public interface CustomerService {
	public Customer insertCustomer(Customer customer);
	public Customer updateCustomer(Customer customer)throws CustomerException;
	public Customer deleteCustomer(Integer customerId)throws CustomerException;
	public List<Customer> viewCustomers();
	public Customer viewCustomer(Integer customerId)throws CustomerException;
	public Customer validateCustomer(String usernae, String password)throws CustomerException;
	
}
