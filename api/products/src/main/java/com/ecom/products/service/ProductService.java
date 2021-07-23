package com.ecom.products.service;

import com.ecom.products.domain.Product;
import com.ecom.products.dto.*;
import com.ecom.products.mapper.products.ProductsMapper;
import com.ecom.products.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductsMapper productsMapperImpl;

    public Iterable<ProductsDto> getProducts(Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Product> pagedResult = productRepository.findAll(pageRequest);
        return productsMapperImpl.productsToProductsDTO(pagedResult);
    }

    public ProductInDetailDto getProduct(Long productId) {
        return productsMapperImpl.productToProductInDetailDto(productRepository.findById(productId).get());
    }

    public void updateReviewRatings(ReviewRatingsDto reviewRatingsDto, Long productId) {
        Product product = productRepository.findById(productId).get();
        product.setRatings(reviewRatingsDto.getRatings());
        product.setReviews(reviewRatingsDto.getReviews());
        productRepository.save(product);
    }

    public void updateStock(UpdateStockDto updateStockDto) {
        for (int index = 0; index < updateStockDto.getProductIdList().length; index++) {
            Optional<Product> product = productRepository.findById(updateStockDto.getProductIdList()[index]);
            if (product.isPresent()) {
                int currentStock = product.get().getStock() - updateStockDto.getQuantityList()[index];
                if (currentStock > 0)
                    product.get().setStock(currentStock);
                productRepository.save(product.get());
            }
        }
    }

    public Long getProductsCount() {
        return productRepository.count();
    }

    public CartProductsMapDto sendProducts(Iterable<ProductIdDto> productIds) {
        List<CartProductsDto> cartProductsDtoList = new ArrayList<>();
        for (ProductIdDto productId : productIds) {
            Optional<Product> product = productRepository.findById(productId.getId());
            if (product.isPresent()) {
                CartProductsDto cartProductsDto = productsMapperImpl.productToCartProductsDto(product.get(), productId.getQuantity());
                cartProductsDtoList.add(cartProductsDto);
            }
        }
       return productsMapperImpl.cartProductsIterableToMap(cartProductsDtoList);
    }
}
