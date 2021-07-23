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
	private int stock;
}
