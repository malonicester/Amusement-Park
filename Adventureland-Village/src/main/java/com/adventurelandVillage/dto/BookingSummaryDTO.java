package com.adventurelandVillage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingSummaryDTO {
  private Long customerId;
  private String activityName;
  private String customerName;
  private Float price;
}
