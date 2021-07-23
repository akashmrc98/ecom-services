package com.example.orders.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderProductsMapDto {
    private Iterable<OrderProductsDto> products;
}
