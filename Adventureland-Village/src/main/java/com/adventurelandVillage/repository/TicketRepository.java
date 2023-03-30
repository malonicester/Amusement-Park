package com.adventurelandVillage.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adventurelandVillage.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Query("select t from Ticket t where t.dateTime=?1")
	public List<Ticket> getTicketByDate(LocalDateTime date);

	@Query("select t from Ticket t where t.dateTime>=?1 and t.dateTime<=?2")
	public List<Ticket> getTicketBetweenDate(LocalDateTime fromDate, LocalDateTime toDate);

	
}
