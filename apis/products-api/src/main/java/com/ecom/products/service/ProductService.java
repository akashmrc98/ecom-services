package com.ecom.products.service;

import com.ecom.products.domain.Product;
import com.ecom.products.dto.ProductInDetailDto;
import com.ecom.products.dto.ProductsDto;
import com.ecom.products.dto.ReviewRatingsDto;
import com.ecom.products.dto.UpdateStockDto;
import com.ecom.products.mapper.products.ProductsMapper;
import com.ecom.products.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public void updateReviewRatings(ReviewRatingsDto reviewRatingsDto, Long productId){
		Product product = productRepository.findById(productId).get();
		product.setRatings(reviewRatingsDto.getRatings());
		product.setReviews(reviewRatingsDto.getReviews());
		productRepository.save(product);
	}

	public void updateStock(UpdateStockDto updateStockDto){
		for (int index = 0; index < updateStockDto.getProductIdList().length; index++) {
			Optional<Product> product = productRepository.findById(updateStockDto.getProductIdList()[index]);
			if(product.isPresent()){
				int currentStock = product.get().getStock() - updateStockDto.getQuantityList()[index];
				if(currentStock > 1)
					product.get().setStock(currentStock);
					productRepository.save(product.get());
			}
		}
	}

	public Long getProductsCount(){
		return productRepository.count();
	}

	public Iterable<ProductsDto> sendProducts(List<Long> productIds){
		List<Product> products = new ArrayList<>();
		for (Long productId: productIds)
			productRepository.findById(productId).ifPresent(products::add);
		return productsMapperImpl.productsToProductsDTO(products) ;
	}
}
