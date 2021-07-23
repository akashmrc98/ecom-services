package com.ecom.cart.mapper;

import com.ecom.cart.domain.Product;
import com.ecom.cart.dto.ProductIdsFromCartDto;
import org.springframework.stereotype.Component;

@Component
public class CartMapperImpl implements CartMapper {

    public ProductIdsFromCartDto productsToProductIdsDto(Iterable<Product> products) {
        return new ProductIdsFromCartDto();
    }

}
