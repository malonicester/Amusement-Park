package com.adventurelandVillage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adventurelandVillage.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
	public Admin findByMobileNumber(String mobileNumber);
	public Admin findByUserName(String username);
	Optional<Admin> findByEmail(String email);
}
