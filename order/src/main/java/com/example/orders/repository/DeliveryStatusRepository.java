package com.example.orders.repository;

import com.example.orders.domain.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus, Long> { }
