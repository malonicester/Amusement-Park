package com.adventurelandVillage.adminrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Admin;
import com.adventurelandVillage.model.Customer;
@Repository
public interface AdminRepo extends JpaRepository<Admin, Long>  {
public Admin findByUserName(String userName);


}
