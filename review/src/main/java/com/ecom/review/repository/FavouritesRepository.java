package com.ecom.review.repository;

import com.ecom.review.domain.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouritesRepository extends JpaRepository<Favourites, Long> {
}
