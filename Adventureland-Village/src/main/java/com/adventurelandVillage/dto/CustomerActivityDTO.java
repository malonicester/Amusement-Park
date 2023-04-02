package com.adventurelandVillage.dto;

import java.util.ArrayList;
import java.util.List;

import com.adventurelandVillage.model.Activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class CustomerActivityDTO {
	private Long customerId;
	private String customerName;
	private List<Activity> activities = new ArrayList<>();
	float charges;

	public CustomerActivityDTO(Long customerId2, String userName, List<Activity> activityByCustomer) {
	}

	public CustomerActivityDTO(Long customerId, String customerName, List<Activity> activities, float charges) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.activities = activities;
		this.charges = charges;
	}

	public CustomerActivityDTO() {
		super();
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public float getCharges() {
		return charges;
	}

	public void setCharges(float charges) {
		this.charges = charges;
	}
	
}
