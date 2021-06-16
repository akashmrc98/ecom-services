package com.ecom.review.controller;

import com.ecom.review.dto.RegisterDto;
import com.ecom.review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createReviewServiceForUser(@RequestBody RegisterDto registerDto){
        reviewService.createReviewServiceForUser(registerDto);
    }
}
