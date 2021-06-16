package com.ecom.products.service;

import com.ecom.products.domain.Product;
import com.ecom.products.dto.ProductInDetailDto;
import com.ecom.products.dto.ProductsDto;
import com.ecom.products.mapper.products.ProductsMapper;
import com.ecom.products.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductsMapper productsMapperImpl;

	public Iterable<ProductsDto> getProducts(Integer pageNo, Integer pageSize, String sortBy){
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pagedResult = productRepository.findAll(pageRequest);
		return productsMapperImpl.productsToProductsDTO(pagedResult);
	}

	public ProductInDetailDto getProduct(Long productId){
		return productsMapperImpl.productToProductInDetailDto(productRepository.findById(productId).get());
	}

	public Long getProductsCount(){
		return productRepository.count();
	}

}
