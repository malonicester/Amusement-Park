package com.adventurelandVillage.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.dto.BookingSummaryDTO;
import com.adventurelandVillage.dto.CustomerActivityDTO;
import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.exception.CustomerException;
import com.adventurelandVillage.exception.LoginException;
import com.adventurelandVillage.exception.TicketException;
import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.model.Ticket;
import com.adventurelandVillage.repository.ActivityRepository;
import com.adventurelandVillage.repository.CustomerRepository;
import com.adventurelandVillage.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public BookingSummaryDTO ticketBookingService(Long activityId) throws ActivityException, TicketException {

		Activity activity = activityRepository.findById(activityId)
				.orElseThrow(() -> new ActivityException("No Activity Found"));

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Customer customer = customerRepository.findByEmail(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));

		Ticket ticket = new Ticket();

		ticket.setCustomers(customer);

		ticket.setActivities(activity);

		customer.getTickets().add(ticket);

		ticketRepository.save(ticket);

		return new BookingSummaryDTO(customer.getCustomerId(), activity.getDescription(), customer.getUserName(),
				activity.getCharges());
	}

	@Override
	public String cancelBookedTicket(Long ticketId) throws TicketException, LoginException {
		Ticket bookedTicket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new TicketException("This Ticket donot exist"));
		bookedTicket.setIsCancelled(true);
		ticketRepository.save(bookedTicket);
		return "Your Ticket is Successfully Cancelled.";
	}

	@Override
	public Set<Ticket> viewAllTicketCustomer(Long customerId)
			throws TicketException, CustomerException, LoginException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerException("Customer doesn't exist"));
		return customer.getTickets();
	}

	@Override
	public CustomerActivityDTO calculateBill() throws TicketException, CustomerException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		Customer customer = customerRepository.findByEmail(authentication.getName()).get();
		Set<Ticket> tickets = customer.getTickets();
		
		if(tickets.isEmpty()) 
			throw new TicketException("Customer has not any booking history");
		
		List<Activity> bookedActivities = tickets.stream().map(ticket->ticket.getActivities())
		.collect(Collectors.toList());
		
		
		float charges = (float)bookedActivities.stream()
        		.map(e->e.getCharges())
        		.mapToDouble(Double::valueOf)
        		.sum();
		return new CustomerActivityDTO(
					customer.getCustomerId(),
					customer.getUserName(),
					bookedActivities,
					charges
				);
	}

	@Override
	public CustomerActivityDTO bookMultiParkComboWithFreeBuffet() throws TicketException, LoginException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Customer customer = customerRepository.findByEmail(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First."));

		List<Activity> bookedActivities = activityRepository.findAll().stream()
				.limit(4).map(activity->{
					Ticket ticket = new Ticket();
					ticket.setCustomers(customer);
					ticket.setActivities(activity);
					customer.getTickets().add(ticket);
				return	ticketRepository.save(ticket);
				})
				.map(ticket->ticket.getActivities())
				.collect(Collectors.toList());
		
		float charges = (float)bookedActivities.stream()
        		.map(e->e.getCharges())
        		.mapToDouble(Double::valueOf)
        		.sum();
		
		return new CustomerActivityDTO(
				customer.getCustomerId(),
				customer.getUserName(),
				bookedActivities,
				charges
				);
	}

}
