package com.adventurelandVillage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adventurelandVillage.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
	public Admin findByUserName(String userName);

}
