package com.ecom.products.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String brand;
	private String category;
	private String subCategory;
	private Double price;
	private Date manufacturedOn;
	private Date createdAt;
	private Date modifiedAt;

	private double ratings;
	private int reviews;

	@OneToMany
	private List<Specification> specifications;
	private Integer stock;

	@OneToMany
	private List<Image> images;
}
