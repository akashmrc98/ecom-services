package com.ecom.cart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {
    private Long productId;
    private Long userId;
}
