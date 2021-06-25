package com.example.orders.dto;

import com.example.orders.domain.Address;
import com.example.orders.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {
    private int totalProducts;
    private double totalPrice;
    private int[] productsQuantityList;
    private Long[] productIdsList;
    private boolean[] isProductsReviewed;
    private String paymentMethod;
    private List<Product> products;
    private Address address;
}
