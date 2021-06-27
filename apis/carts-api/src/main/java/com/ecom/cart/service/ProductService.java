package com.ecom.cart.service;

import com.ecom.cart.domain.Product;
import com.ecom.cart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public void removeProduct(Long productId){
        productRepository.deleteById(productId);
    }

}
