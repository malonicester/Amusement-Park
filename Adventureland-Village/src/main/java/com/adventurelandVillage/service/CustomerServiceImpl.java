package com.adventurelandVillage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.exception.CustomerException;
import com.adventurelandVillage.exception.LoginException;
import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private LoginService loginService;

	@Override
	public Customer insertCustomer(Customer customer) throws LoginException{
			Customer cst= customerRepository.save(customer);
			return cst;
	}

	@Override
	public Customer updateCustomer(Customer customer,String key) throws CustomerException, LoginException {
		Boolean flag= loginService.isLoggedIn(key);
		if(flag) {
			Optional<Customer> cstm=customerRepository.findById(customer.getCustomerId());
			if(cstm.isPresent()) {
				Customer cst= customerRepository.save(customer);
				return cst;
			}
			else
				throw new CustomerException("No any customer found by customerId : "+customer.getCustomerId());
		}
			else throw new LoginException("You have to login first.");
	
	}

	@Override
	public String deleteCustomer(Long customerId,String key) throws CustomerException, LoginException{
		Boolean flag= loginService.isLoggedIn(key);
		if(flag) {
			Optional<Customer> cst=customerRepository.findById(customerId);
			if(cst.isPresent()) {
				customerRepository.deleteById(customerId);
				return "Deleted successfully.";
			}else
				throw new CustomerException("No any customer found by customerId : "+customerId);
		}
		else 
			throw new LoginException("You have to login first.");

	}

	@Override
	public List<Customer> viewCustomers(String key) throws LoginException{
		Boolean flag= loginService.isLoggedIn(key);
		if(flag) {
			List<Customer> clist=customerRepository.findAll();
			if(clist.isEmpty()) {
				throw new CustomerException("No customer found.");
			}
			else	
				return clist;
		}
		else 
			throw new LoginException("You have to login first.");

	}

	@Override
	public Customer viewCustomer(Long customerId,String key) throws CustomerException , LoginException{
		Boolean flag= loginService.isLoggedIn(key);
		if(flag) {
			return customerRepository.findById(customerId).orElseThrow(()->
			new CustomerException("No any customer found by customerId : "+customerId));
		}
		else 
			throw new LoginException("You have to login first.");
	}

	@Override
	public Customer validateCustomer(String username, String password,String key) throws CustomerException , LoginException{
		Boolean flag= loginService.isLoggedIn(key);
		if(flag) {
			Customer cst=customerRepository.findByUserNameAndPassword(username, password);
			if(cst!=null) {
				return cst;
			}
			else
				throw new CustomerException("No any customer found.");
		}
		else 
			throw new LoginException("You have to login first.");
		}
	

}
