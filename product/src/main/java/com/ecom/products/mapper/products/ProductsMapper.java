package com.ecom.products.mapper.products;

import com.ecom.products.domain.Product;
import com.ecom.products.dto.ProductInDetailDto;
import com.ecom.products.dto.ProductsDto;

public interface ProductsMapper {
	 Iterable<ProductsDto> productsToProductsDTO(Iterable<Product> products);
	 ProductInDetailDto productToProductInDetailDto(Product product);
}
