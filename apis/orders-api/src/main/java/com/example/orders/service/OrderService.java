package com.example.orders.service;

import com.example.orders.domain.Order;
import com.example.orders.domain.Purchase;
import com.example.orders.dto.OrderDto;
import com.example.orders.dto.RegisterDto;
import com.example.orders.repository.OrderRepository;
import com.example.orders.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PurchaseRepository purchaseRepository;

    public void createOrderServiceForUser(RegisterDto registerDto){
        Purchase purchase = new Purchase();
        purchase.setUserId(registerDto.getUserId());
        purchaseRepository.save(purchase);
    }

    public void checkoutOrderServiceForUser(OrderDto order, Long userId){
        Purchase purchase = purchaseRepository.findByUserId(userId);

        Order orderData = new Order();
        orderData.setAddress(order.getAddress());
        orderData.setLastModifiedAt(new Date());
        orderData.setProducts(order.getProducts());
        orderData.setPaymentMethod(order.getPaymentMethod());
        orderData.setPurchasedAt(new Date());
        orderData.setTotalPrice(order.getTotalPrice());
        orderData.setTotalProducts(order.getTotalProducts());
        orderData.setProductsQuantityList(order.getProductsQuantityList());
        orderData.setIsProductsReviewed(initIsReviews(order.getProducts().size()));
        orderRepository.save(orderData);

        List<Order> orderList = purchase.getOrders();
        orderList.add(orderData);
        purchase.setOrders(orderList);
        purchaseRepository.save(purchase);
    }

    public void updateIsReviewed(Long orderId, int index){
        Order order = orderRepository.getById(orderId);
        boolean[] isReviewed = order.getIsProductsReviewed();
        isReviewed[index] = true;
        order.setIsProductsReviewed(isReviewed);
        orderRepository.save(order);
    }

    public Iterable<Order> getOrdersByUserId(Long userId){
        return purchaseRepository.findByUserId(userId).getOrders();
    }

    public static boolean[] initIsReviews(int noOfProducts){
        boolean[] isProductReviewed = new boolean[noOfProducts];
        for (int i = 0; i < noOfProducts; i ++)
            isProductReviewed[i] = false;
      return isProductReviewed;
    }

}
