package com.adventurelandVillage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.model.Ticket;
import com.adventurelandVillage.service.TicketService;

@RestController
public class TicketController {
	@Autowired
	private TicketService ticketSerivce;

	@PostMapping("/tickets/{id}")
	public ResponseEntity<Ticket> bookTicketHandler(@RequestBody Ticket ticket, @PathVariable("id") Long activityId,
			@RequestParam String uuid) {
		return new ResponseEntity<Ticket>(ticketSerivce.insertTicketBooking(ticket, activityId, uuid), HttpStatus.OK);
	}
}
