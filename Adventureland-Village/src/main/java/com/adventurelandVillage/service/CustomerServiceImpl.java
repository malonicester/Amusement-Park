package com.adventurelandVillage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.exception.CustomerException;
import com.adventurelandVillage.exception.LoginException;
import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.model.Role;
import com.adventurelandVillage.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Customer insertCustomer(Customer customer) throws LoginException{
		    customer.setRole(Role.ROLE_USER);
		    customer.setPassword(passwordEncoder.encode(customer.getPassword()));
			Customer savedCustomer = customerRepository.save(customer);
			return savedCustomer;
	}

	@Override
	public Customer updateCustomer(Customer updatedCustomer) throws CustomerException, LoginException {
		
		Authentication authentication = SecurityContextHolder
				.getContext()
				.getAuthentication();
		
		Customer customer = customerRepository.findByEmail(authentication.getName())
							.orElseThrow(()->new CustomerException("No User Found"));
		
		if(updatedCustomer.getAddress()!=null) {
			customer.
					setAddress(updatedCustomer.getAddress());
		}
		
		if(updatedCustomer.getMobileNumber()!=null) {
			customer.
				setMobileNumber(updatedCustomer.getMobileNumber());
		}
		
		return customerRepository.save(customer);
	}

	@Override
	public Customer deleteCustomer(Long customerId) throws CustomerException, LoginException {
		Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerException("No User Found with id: "+customerId));
		customerRepository.delete(customer);
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() throws LoginException {
		
		List<Customer> allCustomers = customerRepository.findAll();
		
		if(allCustomers.isEmpty()) 
			throw new CustomerException("No Customers Found");
		return allCustomers;
	}

	@Override
	public Customer findCustomerById(Long customerId) throws CustomerException, LoginException {
		return customerRepository.findById(customerId)
						.orElseThrow(()-> new CustomerException("No Customer Found with id: "+customerId));
	}



}
