package com.ecom.cart.mapper;

import com.ecom.cart.domain.Product;
import com.ecom.cart.dto.ProductIdsFromCartDto;

public interface CartMapper {
    ProductIdsFromCartDto productsToProductIdsDto(Iterable<Product> products);
}
