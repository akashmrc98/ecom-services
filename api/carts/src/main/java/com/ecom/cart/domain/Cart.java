package com.ecom.cart.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	@Column(unique = true)
	private Long userId;

	@OneToMany()
	private List<Product> products = new ArrayList<>();
}
