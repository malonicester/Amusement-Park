package com.adventurelandVillage.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adventurelandVillage.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>,PagingAndSortingRepository<Activity, Long> {

	public Optional<Activity> findByDescription(String name);


	public List<Activity> findByChargesLessThan(float charges);

	@Query(value = "SELECT a.* FROM activity a JOIN ticket t ON a.activity_id = t.activities_activity_id JOIN customer c ON t.customers_customer_id = c.customer_id WHERE c.customer_id = ?1", nativeQuery = true)
	public List<Activity> getCustomerId(Long customerId);

	@Query("select a from Activity a join Ticket t on a.activityId=t.activities.activityId group by t.customers")
	public List<Activity> getCustomerWise();

	@Query(value = "SELECT a.* FROM activity a INNER JOIN ticket t ON a.activity_id = t.activities_activity_id WHERE t.customers_customer_id = ?1 AND t.date_time BETWEEN ?2 AND ?3", nativeQuery = true)
	public List<Activity> getDateBetween(Long customerId, LocalDateTime fromDate, LocalDateTime toDate);

	@Query("select count(a) from Activity a where charges = ?1")
	public int countActivitiesOfCharges(@Param("charges") float charges);

}
