package com.adventurelandVillage.adminrepository;

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
}

