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
	private Integer quantity;
	private boolean isReviewed;
}
