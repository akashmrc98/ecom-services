package com.ecom.cart.dto;

import com.ecom.cart.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {
    private Product product;
    private Long userId;
}
