package com.adventurelandVillage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adventurelandVillage.model.CurrentUserSession;
@Repository
public interface SessionRepo extends JpaRepository<CurrentUserSession, Long> {
public CurrentUserSession findByUuid(String uuid);

}
