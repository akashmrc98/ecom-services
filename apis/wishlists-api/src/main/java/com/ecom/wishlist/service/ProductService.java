package com.ecom.wishlist.service;

import com.ecom.wishlist.domain.Product;
import com.ecom.wishlist.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void saveProduct(Product product){
        productRepository.save(product);
    }

}
