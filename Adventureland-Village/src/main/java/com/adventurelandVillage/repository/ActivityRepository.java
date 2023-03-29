package com.adventurelandVillage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Customer;

import jakarta.validation.constraints.AssertTrue.List;

public interface ActivityRepository extends JpaRepository<Activity, Long>{
  java.util.List<Activity> findByChargesLessThan(float charges);
	
}
