package com.adventurelandVillage.service;

import java.util.Set;

import com.adventurelandVillage.dto.BookingSummaryDTO;
import com.adventurelandVillage.dto.CustomerActivityDTO;
import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.exception.CustomerException;
import com.adventurelandVillage.exception.LoginException;
import com.adventurelandVillage.exception.TicketException;
import com.adventurelandVillage.model.Ticket;

public interface TicketService {

	public BookingSummaryDTO ticketBookingService(Long activityId)
			throws ActivityException, TicketException;

	public String cancelBookedTicket(Long ticketId)
			throws TicketException, LoginException;

	public Set<Ticket> viewAllTicketCustomer(Long customerId)
			throws TicketException, CustomerException, LoginException;

	public CustomerActivityDTO calculateBill()
			throws TicketException,  CustomerException;

	public CustomerActivityDTO bookMultiParkComboWithFreeBuffet()
			throws TicketException, LoginException;
}
