package com.ecom.products.mapper.products;

import com.ecom.products.domain.Product;
import com.ecom.products.dto.ProductInDetailDto;
import com.ecom.products.dto.ProductsDto;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProductsMapperImpl implements ProductsMapper {
	public Iterable<ProductsDto> productsToProductsDTO(Iterable<Product> products){
		List<ProductsDto> productsDtoS = new ArrayList<>();
		for (Product product : products) {
			ProductsDto productsDto = new ProductsDto();
			productsDto.setId(product.getId());
			productsDto.setBrand(product.getBrand());
			productsDto.setPrice(product.getPrice());
			productsDto.setDescription(product.getDescription());
			productsDto.setStock(product.getStock());
			productsDto.setImage(product.getImages().get(0).getContent());
			productsDtoS.add(productsDto);
		}
		return productsDtoS;
	}

	public ProductInDetailDto productToProductInDetailDto(Product product){
		ProductInDetailDto productInDetailDto = new ProductInDetailDto();
		productInDetailDto.setId(product.getId());
		productInDetailDto.setBrand(product.getBrand());
		productInDetailDto.setPrice(product.getPrice());
		productInDetailDto.setDescription(product.getDescription());
//		productInDetailDto.setSpecifications(product.getSpecifications());
		productInDetailDto.setStock(product.getStock());
		productInDetailDto.setImages(product.getImages());
		return productInDetailDto;
	}
}
