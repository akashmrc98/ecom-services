package com.ecom.cart.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartProductsMapDto {
    private Iterable<CartProductsDto> products;
}
