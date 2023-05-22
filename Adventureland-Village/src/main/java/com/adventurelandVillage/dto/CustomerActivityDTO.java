package com.adventurelandVillage.dto;

import java.util.ArrayList;
import java.util.List;

import com.adventurelandVillage.model.Activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerActivityDTO {
	private Long customerId;
	private String customerName;
	private List<Activity> activities = new ArrayList<>();
	private Float charges;
}
