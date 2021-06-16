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
	private Long productId;
	private String description;
	private String brand;
	private String category;
	private String subCategory;
	private Double price;
	private Date manufacturedOn;
	private String[] specifications;

	private Integer stock;

	@OneToOne
	private Image image;
}
