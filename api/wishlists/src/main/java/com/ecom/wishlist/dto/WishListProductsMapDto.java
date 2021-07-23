package com.ecom.wishlist.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WishListProductsMapDto {
    private Iterable<WishListProductsDto> products;
}
