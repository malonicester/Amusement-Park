package com.adventurelandVillage.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude="tickets")
@Entity
public class Customer extends AbstractUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	@JsonIgnore
	@OneToMany(mappedBy = "customers")
	private Set<Ticket> tickets;
}
