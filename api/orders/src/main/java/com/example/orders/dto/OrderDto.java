package com.example.orders.dto;

import com.example.orders.domain.Address;
import com.example.orders.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private int totalProducts;
    private double totalPrice;
    private int[] productsQuantityList;
    private Long[] productIdsList;
    private boolean[] isProductsReviewed;
    private String paymentMethod;
    private Address address;
    private Iterable<OrderProductsDto> products;
    private Date purchasedAt;
    private Date lastModifiedAt;
}
