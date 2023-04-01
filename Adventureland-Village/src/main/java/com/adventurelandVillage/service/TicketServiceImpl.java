package com.adventurelandVillage.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.dto.CustomerTicketDTO;
import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.exception.CustomerException;
import com.adventurelandVillage.exception.LoginException;
import com.adventurelandVillage.exception.TicketException;
import com.adventurelandVillage.model.Activity;
import com.adventurelandVillage.model.CurrentUserSession;
import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.model.Ticket;
import com.adventurelandVillage.repository.ActivityRepository;
import com.adventurelandVillage.repository.CustomerRepository;
import com.adventurelandVillage.repository.SessionRepo;
import com.adventurelandVillage.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private LoginService islogInLogout;

	@Autowired
	private ActivityRepository activityRepo;

	@Autowired
	private SessionRepo sessionRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private TicketRepository ticketRepo;

	@Override
	public Ticket insertTicketBooking(Ticket ticket, Long activityId, String uuid)
			throws ActivityException, TicketException, LoginException {
		if (islogInLogout.isLoggedIn(uuid) == false) {
			throw new LoginException("Please Login as Admin !!!");
		}

		Optional<Activity> optinalActivity = activityRepo.findById(activityId);

		if (optinalActivity.isPresent()) {
			CurrentUserSession currUser = sessionRepo.findByUuid(uuid);

			Optional<Customer> optionalCustomer = customerRepo.findById(currUser.getUserId());

			Customer customer = optionalCustomer.get();

			ticket.setCustomers(customer);

			customer.getTickets().add(ticket);

			ticket.setActivities(optinalActivity.get());

			return ticketRepo.save(ticket);

		}

		throw new ActivityException("Activity not found with ID " + activityId);

	}

	@Override
	public Ticket updateTicketBooking(Long ticketId, Long activityId,String uuid)
			throws ActivityException, TicketException, LoginException {
		if (islogInLogout.isLoggedIn(uuid) == false) {
			throw new LoginException("Please Login as Admin !!!");
		}

		Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(() -> new TicketException("Ticket not found..."));

		Activity activity = activityRepo.findById(activityId)
				.orElseThrow(() -> new ActivityException("Activity not found"));

		ticket.setActivities(activity);
		ticket.setDateTime(LocalDateTime.now());
		ticketRepo.save(ticket);

		return ticket;
	}

	@Override
	public String deleteTicketBooking(Long ticketId, Long customerId,String uuid) throws TicketException, LoginException {
		// use parameter customerId to check if current session customer is same as
		// customer from customerId
		if (islogInLogout.isLoggedIn(uuid) == false) {
			throw new LoginException("Please Login as Admin !!!");
		}
		String res = "Failed,Please try again...";
		Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(() -> new TicketException("Ticket ID not found..."));

		try {
			ticketRepo.delete(ticket);
			res = "Ticket deleted successfully";
		} catch (Exception e) {
			return res;
		}

		return res;
	}

	@Override
	public List<Ticket> viewAllTicketCustomer(Long customerId,String uuid)
			throws TicketException, CustomerException, LoginException {
		// use parameter customerId to check if current session customer is same as
		// customer from customerId
		if (islogInLogout.isLoggedIn(uuid) == false) {
			throw new LoginException("Please Login as Admin !!!");
		}
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("Customer not found.."));

		List<Ticket> listOfTickets = customer.getTickets();

		return listOfTickets;
	}

	@Override
	public CustomerTicketDTO calculateBill(Long customerId,String uuid) throws TicketException, LoginException, CustomerException {
		if (islogInLogout.isLoggedIn(uuid) == false) {
			throw new LoginException("Please Login as Admin !!!!!!");
		}
		float charges = 0;
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("Customer not found.."));

		List<Ticket> ticketList = customer.getTickets();

		for (Ticket t : ticketList) {
			charges += t.getActivities().getCharges();
		}
		CustomerTicketDTO customerTicketDTO = new CustomerTicketDTO();

		customerTicketDTO.setCustomer(customer);
		customerTicketDTO.setTickets(ticketList);
		customerTicketDTO.setTotalAmount(charges);

		return customerTicketDTO;
	}

}
