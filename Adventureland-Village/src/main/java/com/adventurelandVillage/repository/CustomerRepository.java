package com.adventurelandVillage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adventurelandVillage.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{      
	public Customer findByUserNameAndPassword(String userName,String password);
	public Customer findByMobileNumber(String mobileNumber);
}
