package com.adventurelandVillage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
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

	@Size(min = 5)
	private String userName;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "{password.invalid}")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@NotNull
	@NotBlank
	@NotEmpty
	private String address;

	@Column(unique = true)
	@Pattern(regexp = "^[6-9]\\d{9}$",message= "Mobile Number Should be of 10 digits and starts with 6-9")
	private String mobileNumber;

	@Column(unique = true)
	@Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",message = "{email.invalid}")
	private String email;
}
