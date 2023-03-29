package com.adventurelandVillage.adminrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adventurelandVillage.model.CurrentUserSession;

public interface SessionRepo extends JpaRepository<CurrentUserSession, Long> {
public CurrentUserSession findByUuuid(String uuid);

}
