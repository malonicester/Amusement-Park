package com.adventurelandVillage.security.configuration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.adventurelandVillage.repository.AdminRepo;
import com.adventurelandVillage.repository.CustomerRepository;
import com.adventurelandVillage.security.filters.JwtGeneratorFilter;
import com.adventurelandVillage.security.filters.JwtValidatorFilter;
import com.adventurelandVillage.service.userDetailsService.AdminUserDetailService;
import com.adventurelandVillage.service.userDetailsService.CustomerUserDetailsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	private final CustomerRepository customerRepository;
	
	private final AdminRepo adminRepo;
	
	@Bean
	@Order(101)
	public SecurityFilterChain customerSecurityFilterChain(HttpSecurity http) throws Exception{
		return http.
				csrf().disable().securityMatcher("/customers/**")
				.authorizeHttpRequests(auth->{
					auth.requestMatchers(HttpMethod.POST,"/customers/register").permitAll()
					.anyRequest().authenticated();
				}).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors().configurationSource( new CorsConfigurationSource() {
					
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
							
						CorsConfiguration configuration = new CorsConfiguration();
						
						configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
						configuration.setAllowedMethods(Collections.singletonList("*"));
						configuration.setAllowCredentials(true);
						configuration.setAllowedHeaders(Collections.singletonList("*"));
						configuration.setExposedHeaders(Arrays.asList("Authorization"));
						return configuration;
												
					}
				}).and()
				.addFilterAfter(new JwtGeneratorFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JwtValidatorFilter(),BasicAuthenticationFilter.class)
				.userDetailsService(new CustomerUserDetailsService(customerRepository))
				.httpBasic().and().
				build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}
	
	@Bean
	@Order(80)
	public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception{
		return http
				.securityMatcher("/admin/**")
				.csrf().disable()
				.authorizeHttpRequests(auth->{
					auth.requestMatchers(HttpMethod.POST,"/admin/register").permitAll()
					.anyRequest().authenticated();
				})
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors().configurationSource( new CorsConfigurationSource() {
					
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
											
						CorsConfiguration configuration = new CorsConfiguration();
						
						configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
						configuration.setAllowedMethods(Collections.singletonList("*"));
						configuration.setAllowCredentials(true);
						configuration.setAllowedHeaders(Collections.singletonList("*"));
						configuration.setExposedHeaders(Arrays.asList("Authorization"));
						return configuration;
										
					}
				}).and()
				.addFilterAfter(new JwtGeneratorFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JwtValidatorFilter(),BasicAuthenticationFilter.class)
				.userDetailsService(new AdminUserDetailService(adminRepo))
				.httpBasic().and()
				.build();
	}
	
	@Bean
	@Order(50)
	public SecurityFilterChain ticketAndAcitivityFilterChain(HttpSecurity http) throws Exception{
		return http
				.csrf().disable()
				.securityMatcher("/tickets/**","/activities/**")
				.authorizeHttpRequests(auth->{
					auth.requestMatchers(HttpMethod.POST,"/tickets/**").hasAnyRole("ADMIN","USER")
					.requestMatchers(HttpMethod.POST,"/activities/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.GET,"/activities").permitAll()
					.requestMatchers(HttpMethod.GET,"/tickets/**").hasAnyRole("ADMIN","USER")
					.anyRequest().authenticated();
				})
				.sessionManagement(session->{
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				})
				.cors().configurationSource( new CorsConfigurationSource() {
					
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
								
						CorsConfiguration configuration = new CorsConfiguration();
						
						configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
						configuration.setAllowedMethods(Collections.singletonList("*"));
						configuration.setAllowCredentials(true);
						configuration.setAllowedHeaders(Collections.singletonList("*"));
						configuration.setExposedHeaders(Arrays.asList("Authorization"));
						return configuration;
											
					}
				}).and()
				.addFilterAfter(new JwtGeneratorFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JwtValidatorFilter(),BasicAuthenticationFilter.class)
				.httpBasic().and()
				.build();
	}
	
}
