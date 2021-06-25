package com.ecom.wishlist.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Product {
	@Id
	private Long id;
	private String description;
	private String brand;
	private String category;
	private String subCategory;
	private Double price;
	private String[] specifications;
	private Integer stock;
	private Integer quantity;
	@Lob()
	@Column(columnDefinition = "MEDIUMBLOB")
	private byte[] image;
}
