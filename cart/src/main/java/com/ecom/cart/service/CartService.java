package com.ecom.cart.service;

import com.ecom.cart.domain.Cart;
import com.ecom.cart.domain.Product;
import com.ecom.cart.dto.RegisterDto;
import com.ecom.cart.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    private final CartRepository cartRepository;

    public void provideCartServiceForUser(RegisterDto registerDto) {
        Cart cart = new Cart();
        cart.setUserId(registerDto.getUserId());
        cartRepository.save(cart);
        logger.info(registerDto.getUserId().toString());
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
