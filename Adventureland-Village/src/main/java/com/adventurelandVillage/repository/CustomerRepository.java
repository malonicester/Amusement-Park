package com.adventurelandVillage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.model.Ticket;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{      
	public Customer findByUserNameAndPassword(String userName,String password);

	public Customer findByMobileNumber(String mobileNumber);

	@Query("select c.tickets from Customer c where c.customerId = ?1")
	public List<Ticket> getAllTicketsByCustomerId(Long customerId);
}
