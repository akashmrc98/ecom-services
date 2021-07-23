package com.ecom.products.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartProductsMapDto {
    Iterable<CartProductsDto> products;
}
