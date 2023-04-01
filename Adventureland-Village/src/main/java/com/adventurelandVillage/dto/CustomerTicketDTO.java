package com.adventurelandVillage.dto;

import java.util.List;

import com.adventurelandVillage.model.Customer;
import com.adventurelandVillage.model.Ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerTicketDTO {
	
	private Customer customer;

	private List<Ticket> tickets;

	private float totalAmount;

}
