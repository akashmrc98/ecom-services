package com.example.orders.service;

import com.example.orders.domain.Product;
import com.example.orders.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public void saveProduct(Long productIds, int productQuantity){
            Product product = new Product();
            product.setReviewed(false);
            product.setId(productIds);
            product.setQuantity(productQuantity);
            productRepository.save(product);
    }
}
