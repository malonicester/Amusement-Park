package com.adventurelandVillage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.exception.CustomerException;
import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService{
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {

		this.customerRepository = customerRepository;
	}

	@Override
	public Customer insertCustomer(Customer customer) {
		Customer cst= customerRepository.save(customer);
		return cst;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		Optional<Customer> cstm=customerRepository.findById(customer.getCustomerId());
		if(cstm.isPresent()) {
			Customer cst= customerRepository.save(customer);
			return cst;
		}
		else
			throw new CustomerException("No any customer found by customerId : "+customer.getCustomerId());

		
	}

	@Override
	public String deleteCustomer(Long customerId) throws CustomerException {
		Optional<Customer> cst=customerRepository.findById(customerId);
		if(cst.isPresent()) {
			customerRepository.deleteById(customerId);
			return "Deleted successfully.";
		}else
			throw new CustomerException("No any customer found by customerId : "+customerId);
	}

	@Override
	public List<Customer> viewCustomers() {
		List<Customer> clist=customerRepository.findAll();
		if(clist.isEmpty()) {
			throw new CustomerException("No customer found.");
		}
		else	
			return clist;
	}

	@Override
	public Customer viewCustomer(Long customerId) throws CustomerException {
		return customerRepository.findById(customerId).orElseThrow(()->
		new CustomerException("No any customer found by customerId : "+customerId));

	}

	@Override
	public Customer validateCustomer(String username, String password) throws CustomerException {

		Customer cst=customerRepository.validateCustomer(username, password);
		if(cst!=null) {
			return cst;
		}
		else
			throw new CustomerException("No any customer found.");
		
	}

}
