package com.ecom.cart.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
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
