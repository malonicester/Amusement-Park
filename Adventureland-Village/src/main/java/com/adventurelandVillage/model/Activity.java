package com.adventurelandVillage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long activityId;
	private String description;
	private Float charges;
}
