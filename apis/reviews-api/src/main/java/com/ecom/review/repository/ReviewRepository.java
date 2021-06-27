package com.ecom.review.repository;

import com.ecom.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> { }
