package com.example.orders.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Product {
	@Id
	private Long id;
	private String description;
	private String brand;
	private Double price;

	private Integer quantity;
	@Lob()
	@Column(columnDefinition = "MEDIUMBLOB")
	private byte[] image;
	private boolean isReviewed;
}
