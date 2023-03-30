package com.adventurelandVillage.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractUser {

	@Column(unique = true)
	@Size(min = 5)
	private String userName;

	//@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
	private String password;

	@NotNull
	@NotBlank
	@NotEmpty
	//@Min(4)
	private String address;

	@Column(unique = true)
	//@Pattern(regexp = "^[6-9]\\d{9}$")
	private String mobileNumber;

	@Column(unique = true)
	@Email
	private String email;
}
