package com.example.orders.service;

import com.example.orders.domain.Order;
import com.example.orders.dto.RegisterDto;
import com.example.orders.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void createOrderServiceForUser(RegisterDto registerDto){
        Order order = new Order();
        order.setUserId(registerDto.getUserId());
        orderRepository.save(order);
    }

}
