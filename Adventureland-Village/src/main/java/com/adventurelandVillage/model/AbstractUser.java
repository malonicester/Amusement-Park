package com.adventurelandVillage.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractUser {
	@Column(unique = true)
	private String userName;
	private String password;
	private String address;
	@Column(unique = true)
	private String mobileNumber;
	@Column(unique = true)
	private String email;
	
}
