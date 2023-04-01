package com.adventurelandVillage.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.exception.AdminException;
import com.adventurelandVillage.exception.LoginException;
import com.adventurelandVillage.model.Admin;
import com.adventurelandVillage.model.CurrentUserSession;
import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.model.LoginDTO;
import com.adventurelandVillage.model.Role;
import com.adventurelandVillage.repository.AdminRepo;
import com.adventurelandVillage.repository.CustomerRepository;
import com.adventurelandVillage.repository.SessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private SessionRepo sessionRepo;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public String logIntoAccount(LoginDTO dto) throws LoginException {
		// TODO Auto-generated method stub
		Admin existingAdmin = adminRepo.findByMobileNumber(dto.getUserName());
		if (existingAdmin == null) {
			throw new LoginException("please enter valid username");
		}
		Optional<CurrentUserSession> validAdminSessionOpt = sessionRepo.findById(existingAdmin.getAdminId());
		if (validAdminSessionOpt.isPresent()) {
			throw new LoginException("user already logid in with this username");
		}

		if (existingAdmin.getPassword().equals(dto.getPassword())) {
			String key = RandomString.make(6);
			CurrentUserSession currentUserSession = new CurrentUserSession(existingAdmin.getAdminId(), key,
					LocalDateTime.now(), dto.getRole());
			sessionRepo.save(currentUserSession);
			return currentUserSession.toString();
		} else {
			throw new LoginException("please enter a valid password");
		}

	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		CurrentUserSession validAdminSession = sessionRepo.findByUuid(key);
		if (validAdminSession == null) {
			throw new LoginException("User not Logged in with this username");

		}
		sessionRepo.delete(validAdminSession);
		return "Looged Out!";
	}

	@Override
	public String logInAsUser(LoginDTO loginDTO) throws LoginException {
		Customer customer = customerRepository.findByMobileNumber(loginDTO.getUserName());
		if (customer == null)
			throw new LoginException("Please enter valid username");
		Optional<CurrentUserSession> sessionUser = sessionRepo.findById(customer.getCustomerId());
		if (sessionUser.isPresent()) {
			throw new LoginException("User is already logged in");
		}
		if (customer.getPassword().equals(loginDTO.getPassword())) {
			String key = RandomString.make(6);
			CurrentUserSession currentUserSession = new CurrentUserSession(customer.getCustomerId(), key,
					LocalDateTime.now(), loginDTO.getRole());
			CurrentUserSession session = sessionRepo.save(currentUserSession);
			return session.toString();
		}
		throw new LoginException("Please enter a valid password");
	}

	public String logOutUser(String key) throws LoginException {
		CurrentUserSession currentUserSession = sessionRepo.findByUuid(key);
		if (currentUserSession == null) {
			throw new LoginException("User not logged in with this username");
		}

		sessionRepo.delete(currentUserSession);
		return "Logged Out!";
	}

	@Override
	public boolean isLoggedIn(String key) throws LoginException {
		CurrentUserSession currentUserSession = sessionRepo.findByUuid(key);
		if (currentUserSession == null)
			return false;
		return true;
	}

	@Override
	public boolean isAdmin(String uuid) throws AdminException {
		return sessionRepo.findByUuid(uuid).getRole().equals(Role.ADMIN);
	}

}
