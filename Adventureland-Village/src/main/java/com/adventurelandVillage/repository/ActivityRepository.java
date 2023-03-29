package com.adventurelandVillage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Customer;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{
public  List<Activity> findByChargesLessThan(float charges);
	
  @Query("select a from Activity a join Ticket t on a.activityId=t.activities.activityId where t.customers.customerId=?1")
  public List<Activity> getCustomerId(Long customerId);
  
  
  @Query("select a from Activity a join Ticket t on a.activityId=t.activities.activityId group by t.customers")
  public List<Activity> getCustomerWise();
  
  
  @Query("select a from Activity a join Ticket t on a.activityId=t.activities.activityId where t.customers.customerId=?1 and t.dateTime>=?2 and t.dateTime=?3")
  public List<Activity> getDateBetween(Long customerId,LocalDateTime fromDate,LocalDateTime toDate );
  
  
}
