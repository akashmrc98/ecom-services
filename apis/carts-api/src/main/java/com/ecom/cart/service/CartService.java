package com.ecom.cart.service;

import com.ecom.cart.domain.Cart;
import com.ecom.cart.domain.Product;
import com.ecom.cart.dto.RegisterDto;
import com.ecom.cart.repository.CartRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final RestTemplate restTemplate;

    public void provideCartServiceForUser(RegisterDto registerDto) {
        Cart cart = new Cart();
        cart.setUserId(registerDto.getUserId());
        cartRepository.save(cart);
        log.info(registerDto.getUserId().toString());
    }

    public void updateProductQuantityInCart(Long userId, Long productId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId);
        List<Product> products = cart.getProducts();
        products.forEach(product -> {
            if (product.getId().equals(productId))
                product.setQuantity(quantity);
        });
        cart.setProducts(products);
        cartRepository.save(cart);
    }

    public void addProductToCart(Product product, Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        List<Product> products = cart.getProducts();
        products.add(product);
        cart.setProducts(products);
        cartRepository.save(cart);
    }

    public Iterable<Product> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).getProducts();
    }

    public void removeProductFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId);
        List<Product> products = cart.getProducts();
        products.removeIf(product -> product.getId().equals(productId));
        cart.setProducts(products);
        cartRepository.save(cart);
    }

    public void removeAllProductsFromCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        List<Product> products = new ArrayList<>();
        cart.setProducts(products);
        cartRepository.save(cart);
    }

    public int getNoOfProductsInCart(Long userId) {
        return cartRepository.findByUserId(userId).getProducts().size();
    }

}
