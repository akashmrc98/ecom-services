package com.example.orders.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Integer totalProducts;
    private Double totalPrice;
    private Date purchasedAt;
    private Date lastModifiedAt;
    private int[] productsQuantityList;
    private String paymentMethod;

    @Column(unique = true)
    private Long userId;
}