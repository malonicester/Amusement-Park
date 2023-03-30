package com.adventurelandVillage.service;
import java.util.List;

import com.adventurelandVillage.exception.CustomerException;
import com.adventurelandVillage.model.Customer;


public interface CustomerService {
	public Customer insertCustomer(Customer customer);
	public Customer updateCustomer(Customer customer)throws CustomerException;
	public String deleteCustomer(Long customerId)throws CustomerException;
	public List<Customer> viewCustomers();
	public Customer viewCustomer(Long customerId)throws CustomerException;
	public Customer validateCustomer(String username, String password)throws CustomerException;
	
}
