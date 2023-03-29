package com.adventurelandVillage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.exception.CustomerException;
import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository customerRepository;
	
//	@Autowired
//	public CustomerServiceImpl(CustomerRepository customerRepository) {
//
//		this.customerRepository = customerRepository;
//	}

	@Override
	public Customer insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer deleteCustomer(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> viewCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer validateCustomer(String usernae, String password) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

}
