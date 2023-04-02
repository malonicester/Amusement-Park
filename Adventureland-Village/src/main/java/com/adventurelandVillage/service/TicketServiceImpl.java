package com.adventurelandVillage.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.dto.CustomerActivityDTO;
import com.adventurelandVillage.dto.CustomerTicketDTO;
import com.adventurelandVillage.exception.ActivityException;
import com.adventurelandVillage.exception.AdminException;
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
	public Ticket insertTicketBooking(Long customerId, Long activityId, String uuid)
			throws ActivityException, TicketException, LoginException {
		if (islogInLogout.isLoggedIn(uuid) == false) {
			throw new LoginException("Please Login First !!!");
		}

		Optional<Activity> optinalActivity = activityRepo.findById(activityId);

		if (optinalActivity.isPresent()) {
//			CurrentUserSession currUser = sessionRepo.findByUuid(uuid);

//			Optional<Customer> optionalCustomer = customerRepo.findById(currUser.getUserId());

			Customer customer = customerRepo.findById(customerId).get();
			Ticket ticket = new Ticket();
			ticket.setCustomers(customer);

			customer.getTickets().add(ticket);

			ticket.setActivities(optinalActivity.get());

			return ticketRepo.save(ticket);

		}

		throw new ActivityException("Activity not found with ID " + activityId);

	}

	@Override
	public Ticket updateTicketBooking(Long ticketId, Long activityId, String uuid)
			throws ActivityException, TicketException, LoginException {
		if (islogInLogout.isLoggedIn(uuid) == false) {
			throw new LoginException("Please Login First !!!");
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
	public String deleteTicketBooking(Long ticketId, Long customerId, String uuid)
			throws TicketException, LoginException {
		// use parameter customerId to check if current session customer is same as
		// customer from customerId
		if (islogInLogout.isLoggedIn(uuid) == false) {
			throw new LoginException("Please Login First !!!");
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
	public List<Ticket> viewAllTicketCustomer(Long customerId, String uuid)
			throws TicketException, CustomerException, LoginException {
		// use parameter customerId to check if current session customer is same as
		// customer from customerId
		if (islogInLogout.isLoggedIn(uuid) == false) {
			throw new LoginException("Please Login First !!!");
		}
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("Customer not found.."));

		List<Ticket> listOfTickets = customer.getTickets();

		return listOfTickets;
	}

	@Override
	public CustomerTicketDTO calculateBill(Long customerId, String uuid)
			throws TicketException, LoginException, CustomerException {
		if (islogInLogout.isLoggedIn(uuid) == false) {
			throw new LoginException("Please Login First !!!!!!");
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

	@Override

	public CustomerActivityDTO bookMultiParkComboWithFreeBuffet(Long customerId, String uuid)
			throws TicketException, LoginException {

		Optional<Customer> optional = customerRepo.findById(customerId);

		if (!islogInLogout.isLoggedIn(uuid))
			throw new LoginException("You are not logged in please login first");

		if (islogInLogout.isAdmin(uuid))
			throw new AdminException("You should be an Customer to book this Pack");

		if (optional.isEmpty())
			throw new CustomerException("No Customer Found with id " + customerId);

		CustomerActivityDTO customerActivityDTO = new CustomerActivityDTO();

		Customer customer = optional.get();

		customerActivityDTO.setCustomerName(customer.getUserName());
		customerActivityDTO.setCustomerId(customer.getCustomerId());

		for (int i = 1; i <= 4; i++) {

			Ticket ticket = new Ticket();
			ticket.setDateTime(LocalDateTime.now());
			ticket.setCustomers(optional.get());

			Activity activity = activityRepo.findById((long) i).get();

			ticket.setActivities(activity);

			customer.getTickets().add(ticket);

			ticketRepo.save(ticket);

			customerActivityDTO.getActivities().add(activity);
			customerActivityDTO.setCharges(customerActivityDTO.getCharges() + activity.getCharges());
		}
		return customerActivityDTO;
	}

}
