package com.ecom.cart.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {
	@Id
	private Long id;
	private Integer quantity;
}
