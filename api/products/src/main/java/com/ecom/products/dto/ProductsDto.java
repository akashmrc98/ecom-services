package com.ecom.products.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductsDto  {
	private Long id;
	private String description;
	private String brand;
	private Double price;
	private Integer stock;
	private byte[] image;
	private double ratings;
	private int reviews;
}