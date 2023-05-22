package com.adventurelandVillage.service;
import java.util.List;

import com.adventurelandVillage.exception.CustomerException;
import com.adventurelandVillage.exception.LoginException;
import com.adventurelandVillage.model.Customer;


public interface CustomerService {
	
	public Customer insertCustomer(Customer customer);
	public Customer updateCustomer(Customer customer)throws CustomerException,LoginException;
	public Customer deleteCustomer(Long customerId)throws CustomerException,LoginException;
	public List<Customer> getAllCustomers()throws LoginException;
	public Customer findCustomerById(Long customerId)throws CustomerException,LoginException;
	
}
