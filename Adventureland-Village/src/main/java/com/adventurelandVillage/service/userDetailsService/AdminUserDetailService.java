package com.adventurelandVillage.service.userDetailsService;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.model.Admin;
import com.adventurelandVillage.repository.AdminRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminUserDetailService implements UserDetailsService {

	private final AdminRepo adminRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepo.findByEmail(username)
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		return new User(
				admin.getEmail(),
				admin.getPassword(),
				List.of(new SimpleGrantedAuthority(admin.getRole().name()))
				);
	}

}
