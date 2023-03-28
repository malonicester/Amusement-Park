package com.adventurelandVillage.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractUser {
	private String userName;
	private String password;
	private String address;
	private String mobileNumber;
	private String email;
}
