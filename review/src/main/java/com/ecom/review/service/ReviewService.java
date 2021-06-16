package com.ecom.review.service;

import com.ecom.review.domain.Review;
import com.ecom.review.dto.RegisterDto;
import com.ecom.review.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void createReviewServiceForUser(RegisterDto registerDto){
        Review review = new Review();
        review.setUserId(registerDto.getUserId());
        reviewRepository.save(review);
    }
}
