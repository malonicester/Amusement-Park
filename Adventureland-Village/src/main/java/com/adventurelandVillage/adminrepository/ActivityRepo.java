package com.adventurelandVillage.adminrepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adventurelandVillage.model.Activity;

public interface ActivityRepo extends JpaRepository<Activity, Long> {
	public List<Activity> findByCustomerId(Long customerId);
	
	@Query("select a from Activity a group by a.customer")
	public List<Activity> getActivitiesCustomer();
	
	@Query("select a from Activity a order by a.dateTime")
	public List<Activity> getActivitiesDate();
	
	@Query("select a from Activity a where a.customer.customerId=?1 and a.dateTime>=?2 and a.dateTime<=?3")
	public List<Activity> getActivityByCustomerDatebetween(Long customerId,LocalDateTime fromDate,LocalDateTime toDate);
}

