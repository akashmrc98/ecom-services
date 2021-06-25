package com.ecom.review.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ReviewDto {
    private String headLine;
    private String description;
    private int rating;
    private Date reviewedOn;
    private String username;
}
