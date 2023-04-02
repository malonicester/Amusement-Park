package com.adventurelandVillage.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {
	private String userName;
	private String password;
	@NotNull
	private Role role;
}
