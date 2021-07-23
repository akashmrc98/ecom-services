package com.example.orders.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderProductsDto {
	private Long id;
	private String description;
	private String brand;
	private Double price;
	private Integer stock;
	private byte[] image;
	private int quantity;
}