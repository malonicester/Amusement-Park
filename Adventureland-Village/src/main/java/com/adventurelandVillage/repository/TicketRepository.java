package com.adventurelandVillage.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.adventurelandVillage.dto.CustomerActivityDTO;
import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>,PagingAndSortingRepository<Ticket, Long>{

	@Query("select t from Ticket t where t.dateTime=?1")
	public List<Ticket> getTicketByDate(LocalDateTime date);

	@Query("select t from Ticket t where t.dateTime>=?1 and t.dateTime<=?2")
	public List<Ticket> getTicketBetweenDate(LocalDateTime fromDate, LocalDateTime toDate);

	@Query("select t.activities from Ticket t where t.customers.customerId=?1")
	public List<Activity> getActivityByCustomer(Long customerId);

	@Query("Select t.activities from Ticket t where t.dateTime between ?1 and ?2")
	public List<Activity> getDateBetween(Long customerId,LocalDateTime fromDate,LocalDateTime toDate );
	
	@Query("Select new com.adventurelandVillage.dto.CustomerActivityDTO(t.customers.customerId,t.customers.userName,t.activities)  from Ticket t")
	public List<CustomerActivityDTO> getCustomerWise();
}
