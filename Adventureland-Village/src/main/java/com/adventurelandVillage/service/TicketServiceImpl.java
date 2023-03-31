package com.adventurelandVillage.service;

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
			throw new LoginException("Please Login first !!!");
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
	public Ticket updateTicketBooking(Long ticketId, Long activityId)
			throws ActivityException, TicketException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket deleteTicketBooking(Long ticketId) throws TicketException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> viewAllTicketCustomer() throws TicketException, CustomerException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerTicketDTO calculateBill() throws TicketException, LoginException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

}
