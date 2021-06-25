package com.ecom.review.service;

import com.ecom.review.domain.Favourites;
import com.ecom.review.domain.ProductReview;
import com.ecom.review.domain.Review;
import com.ecom.review.dto.LikeDto;
import com.ecom.review.dto.RegisterDto;
import com.ecom.review.dto.ReviewDto;
import com.ecom.review.dto.ReviewRatingsDto;
import com.ecom.review.repository.FavouritesRepository;
import com.ecom.review.repository.ProductReviewRepository;
import com.ecom.review.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductReviewRepository productReviewRepository;
    private final FavouritesRepository favouritesRepository;

    public void createReviewServiceForUser(RegisterDto registerDto) {
        ProductReview productReview = new ProductReview();
        productReview.setId(registerDto.getUserId());
        productReviewRepository.save(productReview);
    }

    public Review saveReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setUsername(reviewDto.getUsername());
        review.setReviewedOn(new Date());
        review.setDescription(reviewDto.getDescription());
        review.setRating(reviewDto.getRating());
        review.setHeadLine(reviewDto.getHeadLine());
        review.setFavourites(new ArrayList<>());
        reviewRepository.save(review);
        return review;
    }

    public void saveReviewToProductList(Review review, Long productId) {
        Optional<ProductReview> productReview = productReviewRepository.findById(productId);
        if(productReview.isPresent()){
            List<Review> reviewsList = (List<Review>) getProductReviewsById(productId);
            reviewsList.add(review);
            productReview.get().setReviews(reviewsList);
            productReviewRepository.save(productReview.get());
        }
        if(productReview.isEmpty()){
            ProductReview productReviewNew = new ProductReview();
            List<Review> reviewsList = new ArrayList<>();
            reviewsList.add(review);
            productReviewNew.setId(productId);
            productReviewNew.setReviews(reviewsList);
            productReviewRepository.save(productReviewNew);
        }
    }

    public void upVoteReview(LikeDto like){
        System.out.println(like.getReviewId());
        System.out.println(like.getUserId());
        Optional<Review> reviewOptional = reviewRepository.findById(like.getReviewId());
        if(reviewOptional.isPresent()){
           List<Favourites> favouritesList = reviewOptional.get().getFavourites();
           Favourites favourites = new Favourites();
           favourites.setUserId(like.getUserId());
           favouritesRepository.save(favourites);
           favouritesList.add(favourites);
           reviewOptional.get().setFavourites(favouritesList);
           reviewRepository.save(reviewOptional.get());
        }
    }


    // DRY
    public Iterable<Review> getProductReviewsById(Long productId) {
        Optional<ProductReview> productReview = productReviewRepository.findById(productId);
        return productReview.<Iterable<Review>>map(ProductReview::getReviews).orElse(new ArrayList<>());
    }

    public ReviewRatingsDto getReviewRatingsOfProduct(Long productId) {
        List<Review> reviews = (List<Review>) getProductReviewsById(productId);
        ReviewRatingsDto reviewRatingsDto = new ReviewRatingsDto();
        reviewRatingsDto.setReviews(reviews.size());
        reviewRatingsDto.setRatings(getRatings(reviews));
        return reviewRatingsDto;
    }

    public double getRatings(List<Review> reviews) {
        double ratings = 0;
        for (Review review : reviews)
            ratings += review.getRating();
        return (ratings / reviews.size());
    }

}

