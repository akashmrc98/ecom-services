package com.ecom.review.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String headLine;
    private String description;
    private int rating;
    private Date reviewedOn;
    private String username;

    @OneToMany
    private List<Favourites> favourites;
}
