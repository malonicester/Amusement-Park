package com.adventurelandVillage.service;

import java.util.List;

import com.adventurelandVillage.dto.CustomerTicketDTO;
import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.exception.CustomerException;
import com.adventurelandVillage.exception.LoginException;
import com.adventurelandVillage.exception.TicketException;
import com.adventurelandVillage.model.Ticket;

public interface TicketService {

	public Ticket insertTicketBooking(Ticket ticket, Long activityId, String uuid) throws ActivityException, TicketException, LoginException;

	public Ticket updateTicketBooking(Long ticketId, Long activityId) throws ActivityException, TicketException, LoginException;

	public Ticket deleteTicketBooking(Long ticketId) throws TicketException, LoginException;

	public List<Ticket> viewAllTicketCustomer() throws TicketException, CustomerException, LoginException;

	public CustomerTicketDTO calculateBill() throws TicketException, LoginException, CustomerException;

}
