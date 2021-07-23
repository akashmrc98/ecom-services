package com.ecom.products.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Specification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long specificationId;

	private String specification;
}
