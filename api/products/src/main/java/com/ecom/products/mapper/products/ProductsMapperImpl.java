package com.ecom.products.mapper.products;

import com.ecom.products.domain.Product;
import com.ecom.products.dto.CartProductsDto;
import com.ecom.products.dto.CartProductsMapDto;
import com.ecom.products.dto.ProductInDetailDto;
import com.ecom.products.dto.ProductsDto;
import com.ecom.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ProductsMapperImpl implements ProductsMapper {

	private final ProductRepository productRepository;

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
			productsDto.setReviews(product.getReviews());
			productsDto.setRatings(product.getRatings());
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

	public CartProductsDto productToCartProductsDto(Product product, Integer quantity){
		CartProductsDto cartProductsDto = new CartProductsDto();
		cartProductsDto.setId(product.getId());
		cartProductsDto.setImage(product.getImages().get(0).getContent());
		cartProductsDto.setPrice(product.getPrice());
		cartProductsDto.setStock(product.getStock());
		cartProductsDto.setBrand(product.getBrand());
		cartProductsDto.setDescription(product.getDescription());
		cartProductsDto.setQuantity(quantity);
		return cartProductsDto;
	}

	public CartProductsMapDto cartProductsIterableToMap(Iterable<CartProductsDto> cartProductsDtos){
		CartProductsMapDto cartProductsMapDto = new CartProductsMapDto();
		cartProductsMapDto.setProducts(cartProductsDtos);
		return cartProductsMapDto;
	}

}
