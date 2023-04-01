package com.adventurelandVillage.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrentUserSession {
	@Id
	@Column(unique = true)
	private Long userId;
	private String uuid;
	private LocalDateTime localDateTime;
	@Enumerated(EnumType.STRING)
	private Role role;
}
