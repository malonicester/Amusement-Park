package com.adventurelandVillage.service.userDetailsService;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerUserDetailsService implements UserDetailsService {

	private final CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(username)
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		return new User(
				customer.getEmail(), 
				customer.getPassword(), 
				List.of(new SimpleGrantedAuthority(customer.getRole().name()))
				);
	}

}
