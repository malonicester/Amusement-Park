package com.adventurelandVillage.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adventurelandVillage.dto.BookingSummaryDTO;
import com.adventurelandVillage.dto.CustomerActivityDTO;
import com.adventurelandVillage.model.Ticket;
import com.adventurelandVillage.service.TicketService;

@RestController
public class TicketController {
	
	@Autowired
	private TicketService ticketSerivce;

	@PostMapping("/tickets")
	public ResponseEntity<BookingSummaryDTO> bookTicketHandler(@RequestParam Long activityId){
		return new ResponseEntity<BookingSummaryDTO>(
				ticketSerivce.ticketBookingService(activityId),
				HttpStatus.CREATED
				);
	}
	
	@PostMapping("/tickets/{ticketId}")
	public ResponseEntity<String> cancelTicketHandler(@PathVariable Long ticketId){
		return new ResponseEntity<String>(
				ticketSerivce.cancelBookedTicket(ticketId),
				HttpStatus.OK
				);
	}
	
	@GetMapping("/tickets/{customerId}")
	public ResponseEntity<Set<Ticket>> viewAllTicketsOfCustomerHandler(@PathVariable Long customerId){
		return new ResponseEntity<Set<Ticket>>(
				ticketSerivce.viewAllTicketCustomer(customerId),
				HttpStatus.OK
				);
	}
	
	@GetMapping("/tickets")
	public ResponseEntity<CustomerActivityDTO> calculateBillOfCustomer(){
		return new ResponseEntity<CustomerActivityDTO>(
				ticketSerivce.calculateBill(),
				HttpStatus.OK
				);
	}
	
	@PostMapping("/tickets/combo/multipark")
	public ResponseEntity<CustomerActivityDTO> bookMultiParkComboWithFreeBuffet(){
		return new ResponseEntity<CustomerActivityDTO>(
				ticketSerivce.bookMultiParkComboWithFreeBuffet(),
				HttpStatus.CREATED
				);
	}
	
	
}
