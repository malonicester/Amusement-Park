package com.adventurelandVillage.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.exception.LoginException;
import com.adventurelandVillage.model.Admin;
import com.adventurelandVillage.model.CurrentUserSession;
import com.adventurelandVillage.model.LoginDTO;
import com.adventurelandVillage.repository.AdminRepo;
import com.adventurelandVillage.repository.SessionRepo;

import net.bytebuddy.utility.RandomString;
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private SessionRepo sessionRepo;

	@Override
	public String logIntoAccount(LoginDTO dto) throws LoginException {
		// TODO Auto-generated method stub
		Admin existingAdmin=adminRepo.findByUserName(dto.getUserName());
		if(existingAdmin==null) {
		throw new LoginException("please enter valid username");
		}
		Optional<CurrentUserSession> validAdminSessionOpt=sessionRepo.findById(existingAdmin.getAdminId());
		if(validAdminSessionOpt.isPresent()) {
			throw new LoginException("user already logid in with this username");
		}
		
		if(existingAdmin.getPassword().equals(dto.getPassword())) {
			String key=RandomString.make(6);
			CurrentUserSession currentUserSession=new CurrentUserSession(existingAdmin.getAdminId(),key,LocalDateTime.now());
					sessionRepo.save(currentUserSession);
			return currentUserSession.toString();
		}
		else {
			throw new LoginException("please enter a valid password");
		}
		
		
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		// TODO Auto-generated method stub
		CurrentUserSession validAdminSession=sessionRepo.findByUuid(key);
		if(validAdminSession==null) {
			throw new LoginException("User not Logged in with this username");
			
		}
		sessionRepo.delete(validAdminSession);
		return "Looged Out!";
	}

}
