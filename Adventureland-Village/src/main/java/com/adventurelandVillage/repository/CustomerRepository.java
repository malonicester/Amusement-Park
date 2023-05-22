package com.adventurelandVillage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.model.Ticket;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>,PagingAndSortingRepository<Customer, Long>{      
	public Customer findByMobileNumberAndPassword(String userName,String password);

	public Customer findByMobileNumber(String mobileNumber);

	Optional<Customer> findByEmail(String email);
	
	@Query("select c.tickets from Customer c where c.customerId = ?1")
	public List<Ticket> getAllTicketsByCustomerId(Long customerId);
}
