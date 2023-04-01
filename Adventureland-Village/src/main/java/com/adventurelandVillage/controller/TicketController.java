package com.adventurelandVillage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.dto.CustomerTicketDTO;
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
	
	@PutMapping("/tickets/update/{ticketId}/{activityId}")
	public ResponseEntity<?> updateTicketHandler(@PathVariable("ticketId") Long ticketId, @PathVariable("activityId") Long activityId,@RequestParam String uuid){
		
		Ticket ticket=this.ticketSerivce.updateTicketBooking(ticketId, activityId, uuid);
		
		return ResponseEntity.ok(ticket);
		
	}
	
	@DeleteMapping("/tickets/delete")
	public ResponseEntity<?> deleteTicketHandler(@RequestParam(value = "ticketId",required = true) Long ticketId,@RequestParam(value = "customerId",required = true) Long customerId,@RequestParam String uuid){
		String message=this.ticketSerivce.deleteTicketBooking(ticketId, customerId, uuid);
		
		return ResponseEntity.ok(message);
	}
	
	@GetMapping("/tickets/getAllTickets")
	public ResponseEntity<?> getAllTicketsHandler(@RequestParam(value = "customerId",required = true) Long customerId,@RequestParam String uuid){
		List<Ticket> tickets=this.ticketSerivce.viewAllTicketCustomer(customerId, uuid);
		return ResponseEntity.ok(tickets);
	}
	
	@GetMapping("/tickets/bill")
	public ResponseEntity<?> calculateBillHandler(@RequestParam(value = "customerId",required = true) Long customerId,@RequestParam String uuid){
		CustomerTicketDTO customerTicketDto=this.ticketSerivce.calculateBill(customerId, uuid);
		return ResponseEntity.ok(customerTicketDto);
	}
}
