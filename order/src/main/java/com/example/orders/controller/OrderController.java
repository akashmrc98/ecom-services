package com.example.orders.controller;

import com.example.orders.client.ProductClient;
import com.example.orders.domain.Order;
import com.example.orders.dto.OrderDto;
import com.example.orders.dto.RegisterDto;
import com.example.orders.dto.UpdateStockDto;
import com.example.orders.service.AddressService;
import com.example.orders.service.OrderService;
import com.example.orders.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final AddressService addressService;
    private final ProductService productService;
    private final ProductClient productClient;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createOrderService(@RequestBody RegisterDto registerDto) {
        orderService.createOrderServiceForUser(registerDto);
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void checkoutOrder(
            @RequestBody OrderDto order,
            @PathVariable("userId") Long userId
    ) {
        productService.saveProduct(order.getProducts(), order.getProductIdsList());
        addressService.saveAddress(order.getAddress());
        orderService.checkoutOrderServiceForUser(order, userId);
        UpdateStockDto updateStockDto = new UpdateStockDto();
        updateStockDto.setProductIdList(order.getProductIdsList());
        updateStockDto.setQuantityList(order.getProductsQuantityList());
        productClient.updateProductReviewsAndRatings(updateStockDto);
    }

    @PostMapping("/{orderId}/products/{index}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProductIsReviewed(
            @PathVariable("orderId") Long orderId,
            @PathVariable("index") int index
    ) {
        orderService.updateIsReviewed(orderId, index);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Iterable<Order>> getOrdersByUserId(@PathVariable("userId") Long userId) {
        Iterable<Order> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}

