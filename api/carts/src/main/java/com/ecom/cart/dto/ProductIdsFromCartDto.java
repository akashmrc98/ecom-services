package com.ecom.cart.dto;

import com.ecom.cart.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductIdsFromCartDto {
	private Iterable<Product> products;
}
