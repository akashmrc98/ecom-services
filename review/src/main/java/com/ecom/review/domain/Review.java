package com.ecom.review.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(unique = true)
    private Long userId;

    private String headLine;
    private String description;
    private int rating;
    private Date reviewedOn;
    private String username;
    private int favourites;
}
