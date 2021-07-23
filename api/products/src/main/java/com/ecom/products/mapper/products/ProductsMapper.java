package com.ecom.products.mapper.products;

import com.ecom.products.domain.Product;
import com.ecom.products.dto.CartProductsDto;
import com.ecom.products.dto.CartProductsMapDto;
import com.ecom.products.dto.ProductInDetailDto;
import com.ecom.products.dto.ProductsDto;

public interface ProductsMapper {
    Iterable<ProductsDto> productsToProductsDTO(Iterable<Product> products);
    ProductInDetailDto productToProductInDetailDto(Product product);
    CartProductsDto productToCartProductsDto(Product product, Integer quantity);
    CartProductsMapDto cartProductsIterableToMap(Iterable<CartProductsDto> cartProductsDtos);
}
