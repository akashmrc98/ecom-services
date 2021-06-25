package com.ecom.review.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Favourites {
    @Id
    private Long userId;
}

