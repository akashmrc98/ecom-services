package com.example.orders.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class DeliveryStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deliveryStatusId;
	private String currentLocation;
	private Date packageReachedAt;
	private Date packageLeftAt;
	private String description;
}
