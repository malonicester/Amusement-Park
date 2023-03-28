package com.adventurelandVillage.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ticketId;
	@ManyToOne
	private Customer customers;
	@OneToOne
	private Activity activities;

	private LocalDateTime dateTime;
}
