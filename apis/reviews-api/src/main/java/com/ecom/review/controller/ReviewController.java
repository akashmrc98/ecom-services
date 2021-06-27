package com.ecom.review.controller;

import com.ecom.review.client.OrderClient;
import com.ecom.review.client.ProductClient;
import com.ecom.review.domain.Review;
import com.ecom.review.dto.LikeDto;
import com.ecom.review.dto.RegisterDto;
import com.ecom.review.dto.ReviewDto;
import com.ecom.review.dto.ReviewRatingsDto;
import com.ecom.review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ProductClient productClient;
    private final OrderClient orderClient;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createReviewServiceForUser(@RequestBody RegisterDto registerDto) {
        reviewService.createReviewServiceForUser(registerDto);
    }

    @PostMapping("/products/{productId}/{orderId}/{index}")
    @ResponseStatus(HttpStatus.OK)
    public void saveReview(
            @RequestBody ReviewDto reviewDto,
            @PathVariable("productId") Long productId,
            @PathVariable("orderId") Long orderId,
            @PathVariable("index") int index
    ) {
        Review review = reviewService.saveReview(reviewDto);
        reviewService.saveReviewToProductList(review, productId);
        ReviewRatingsDto reviewRatingsDto = reviewService.getReviewRatingsOfProduct(productId);
        productClient.updateProductReviewsAndRatings(reviewRatingsDto, productId);
        orderClient.updateIsReviewedProduct(orderId, index);
    }

    @PostMapping("/up-vote")
    @ResponseStatus(HttpStatus.OK)
    public void upVote(@RequestBody LikeDto likeDto){
        reviewService.upVoteReview(likeDto);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Iterable<Review>> getReviewByProductId(@PathVariable("productId") Long productId){
        return new ResponseEntity<>(reviewService.getProductReviewsById(productId), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}/review")
    public ResponseEntity<ReviewRatingsDto> getReviewsByProductId(@PathVariable("productId") Long productId){
        return new ResponseEntity<>(reviewService.getReviewRatingsOfProduct(productId), HttpStatus.OK);
    }
}
