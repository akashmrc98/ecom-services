package com.example.orders.service;

import com.example.orders.domain.Order;
import com.example.orders.domain.Product;
import com.example.orders.domain.Purchase;
import com.example.orders.dto.OrderDto;
import com.example.orders.mapper.OrderMapper;
import com.example.orders.repository.OrderRepository;
import com.example.orders.repository.ProductRepository;
import com.example.orders.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PurchaseRepository purchaseRepository;
    private final OrderMapper orderMapperImpl;
    private final ProductRepository productRepository;

    public Purchase createOrderServiceForUser(Long userId){
        Purchase purchase = new Purchase();
        purchase.setUserId(userId);
        purchase.setOrders(new ArrayList<>());
        purchaseRepository.save(purchase);
        return purchase;
    }

    public void checkoutOrderServiceForUser(OrderDto order, Long userId){
        Optional<Purchase> purchase = purchaseRepository.findByUserId(userId);

        if(purchase.isPresent()){
            Order orderData = new Order();
            orderData.setAddress(order.getAddress());
            orderData.setLastModifiedAt(new Date());
            orderData.setPaymentMethod(order.getPaymentMethod());
            orderData.setPurchasedAt(new Date());
            orderData.setTotalPrice(order.getTotalPrice());
            orderData.setTotalProducts(order.getTotalProducts());

            orderData.setProductsQuantityList(order.getProductsQuantityList());

            List<Product> products = new ArrayList<>();
            boolean[] isReviewed = new boolean[order.getProductIdsList().length];
            for (int i = 0; i < order.getProductIdsList().length; i++) {
                Product product = new Product();
                product.setId(order.getProductIdsList()[i]);
                product.setQuantity(order.getProductsQuantityList()[i]);
                product.setReviewed(false);
                products.add(product);
                isReviewed[i] = false;
                productRepository.save(product);
            }

            orderData.setIsProductsReviewed(isReviewed);
            orderData.setProducts(products);

            orderRepository.save(orderData);

            List<Order> orderList = purchase.get().getOrders();
            orderList.add(orderData);
            purchase.get().setOrders(orderList);
            purchaseRepository.save(purchase.get());
        }

        if(purchase.isEmpty()){

            Purchase newPurchase = createOrderServiceForUser(userId);

            Order orderData = new Order();
            orderData.setAddress(order.getAddress());
            orderData.setLastModifiedAt(new Date());
            orderData.setPaymentMethod(order.getPaymentMethod());
            orderData.setPurchasedAt(new Date());
            orderData.setTotalPrice(order.getTotalPrice());
            orderData.setTotalProducts(order.getTotalProducts());

            orderData.setIsProductsReviewed(order.getIsProductsReviewed());
            orderData.setProductsQuantityList(order.getProductsQuantityList());

            List<Product> products = new ArrayList<>();
            boolean[] isReviewed = new boolean[order.getProductIdsList().length];

            for (int i = 0; i < order.getProductIdsList().length; i++) {
                Product product = new Product();
                product.setId(order.getProductIdsList()[i]);
                product.setQuantity(order.getProductsQuantityList()[i]);
                product.setReviewed(false);
                products.add(product);
                productRepository.save(product);
                isReviewed[i] = false;
            }

            orderData.setIsProductsReviewed(isReviewed);
            orderData.setProducts(products);

            orderRepository.save(orderData);

            List<Order> orderList = newPurchase.getOrders();
            orderList.add(orderData);
            newPurchase.setOrders(orderList);
            purchaseRepository.save(newPurchase);
        }

    }

    public void updateIsReviewed(Long orderId, int index){
        Order order = orderRepository.getById(orderId);
        boolean[] isReviewed = order.getIsProductsReviewed();
        isReviewed[index] = true;
        order.setIsProductsReviewed(isReviewed);
        orderRepository.save(order);
    }

    public Iterable<OrderDto> getOrdersByUserId(Long userId){

       Optional<Purchase> purchase = purchaseRepository.findByUserId(userId);
       if(purchase.isPresent()){
           Iterable<Order> orders = purchase.get().getOrders();
           return orderMapperImpl.ordersToOrdersDto(orders);
       }

        return new ArrayList<>();
    }


    public static boolean[] initIsReviews(int noOfProducts){
        boolean[] isProductReviewed = new boolean[noOfProducts];
        for (int i = 0; i < noOfProducts; i ++)
            isProductReviewed[i] = false;
      return isProductReviewed;
    }

}
