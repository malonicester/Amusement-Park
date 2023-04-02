package com.adventurelandVillage.service;

import java.util.List;

import com.adventurelandVillage.dto.CustomerActivityDTO;
import com.adventurelandVillage.dto.CustomerTicketDTO;
import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.exception.CustomerException;
import com.adventurelandVillage.exception.LoginException;
import com.adventurelandVillage.exception.TicketException;
import com.adventurelandVillage.model.Ticket;

public interface TicketService {

	public Ticket insertTicketBooking(Long  customerId, Long activityId, String uuid)
			throws ActivityException, TicketException, LoginException;

	public Ticket updateTicketBooking(Long ticketId, Long activityId, String uuid)
			throws ActivityException, TicketException, LoginException;

	public String deleteTicketBooking(Long ticketId, Long customerId, String uuid)
			throws TicketException, LoginException;

	public List<Ticket> viewAllTicketCustomer(Long customerId, String uuid)
			throws TicketException, CustomerException, LoginException;

	public CustomerTicketDTO calculateBill(Long customerId, String uuid)
			throws TicketException, LoginException, CustomerException;

	public CustomerActivityDTO bookMultiParkComboWithFreeBuffet(Long customerId, String uuid)
			throws TicketException, LoginException;
}
