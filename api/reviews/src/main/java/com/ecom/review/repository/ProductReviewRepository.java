package com.ecom.review.repository;

import com.ecom.review.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> { }
