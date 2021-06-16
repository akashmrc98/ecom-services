package com.example.orders.controller;

import com.example.orders.dto.RegisterDto;
import com.example.orders.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createOrderService(@RequestBody RegisterDto registerDto){
        orderService.createOrderServiceForUser(registerDto);
    }
}
