package com.ecom.review.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class ProductReview {
    @Id
    private Long id;
    @OneToMany
    private List<Review> reviews;
}
