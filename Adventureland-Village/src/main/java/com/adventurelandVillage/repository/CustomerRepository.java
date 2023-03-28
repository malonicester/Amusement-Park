package com.adventurelandVillage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adventurelandVillage.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
