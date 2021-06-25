package com.ecom.wishlist.repository;

import com.ecom.wishlist.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
