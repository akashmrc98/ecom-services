package com.example.orders.mapper;

import com.example.orders.client.ProductClient;
import com.example.orders.domain.Order;
import com.example.orders.dto.OrderDto;
import com.example.orders.dto.OrderProductsMapDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements  OrderMapper{

    private final ProductClient productClient;

    @Override
    public Iterable<OrderDto> ordersToOrdersDto(Iterable<Order> orders) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        orders.forEach(order -> {
           OrderProductsMapDto orderProductsMapDto = productClient.getOrderProducts(order.getProducts());

           OrderDto orderDto = new OrderDto();

           orderDto.setOrderId(order.getOrderId());
           orderDto.setAddress(order.getAddress());
           orderDto.setPaymentMethod(order.getPaymentMethod());
           orderDto.setAddress(order.getAddress());
           orderDto.setTotalPrice(order.getTotalPrice());
           orderDto.setTotalProducts(order.getTotalProducts());
           orderDto.setPurchasedAt(order.getPurchasedAt());
           orderDto.setLastModifiedAt(order.getLastModifiedAt());

           orderDto.setIsProductsReviewed(order.getIsProductsReviewed());
           orderDto.setProductsQuantityList(order.getProductsQuantityList());

            orderDto.setProducts(orderProductsMapDto.getProducts());
            orderDtoList.add(orderDto);
        });
        return orderDtoList;
    }
}
