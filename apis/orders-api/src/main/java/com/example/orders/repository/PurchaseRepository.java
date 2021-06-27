package com.example.orders.repository;

import com.example.orders.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Purchase findByUserId(Long userId);
}
