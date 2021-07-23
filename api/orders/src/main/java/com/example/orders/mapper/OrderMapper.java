package com.example.orders.mapper;

import com.example.orders.domain.Order;
import com.example.orders.dto.OrderDto;

public interface OrderMapper {
    Iterable<OrderDto> ordersToOrdersDto(Iterable<Order> orders);
}
