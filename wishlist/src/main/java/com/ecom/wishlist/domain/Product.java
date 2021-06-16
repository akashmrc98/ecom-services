package com.ecom.wishlist.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
	private String[] specifications;
	private Integer stock;

	@OneToOne
	private Image image;
}
