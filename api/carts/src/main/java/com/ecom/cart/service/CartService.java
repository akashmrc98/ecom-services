package com.ecom.cart.service;

import com.ecom.cart.client.ProductsClient;
import com.ecom.cart.domain.Cart;
import com.ecom.cart.domain.Product;
import com.ecom.cart.dto.CartProductsMapDto;
import com.ecom.cart.mapper.CartMapper;
import com.ecom.cart.repository.CartRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductsClient productsClient;
    private final CartMapper cartMapperImpl;

    public Cart provideCartServiceForUser(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProducts(new ArrayList<>());
        cartRepository.save(cart);
        return cart;
    }

    public void updateProductQuantityInCart(Long userId, Long productId, int quantity) {
        try {
            Optional<Cart> cart = cartRepository.findByUserId(userId);
            if (cart.isPresent()) {
                List<Product> products = cart.get().getProducts();
                products.forEach(product -> {
                    if (product.getId().equals(productId))
                        product.setQuantity(quantity);
                });
                cart.get().setProducts(products);
                cartRepository.save(cart.get());
            }
        } catch (NullPointerException e) {
            log.error(e.getMessage() + e.getCause());
        }
    }

    public void addProductToCart(Product product, Long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);

        if (cart.isPresent()) {
            List<Product> products = cart.get().getProducts();
            products.add(product);
            cart.get().setProducts(products);
            cartRepository.save(cart.get());
        }

        if (cart.isEmpty()) {
            Cart newCart = provideCartServiceForUser(userId);
            List<Product> newCartProducts = newCart.getProducts();
            newCartProducts.add(product);
            newCart.setProducts(newCartProducts);
            cartRepository.save(newCart);
        }

    }

    public CartProductsMapDto getCartByUserId(Long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if (cart.isPresent()){
            Iterable<Product> products = cart.get().getProducts();
            return productsClient.getCartProducts(products);
        }

        return new CartProductsMapDto();
    }

    public void removeProductFromCart(Long userId, Long productId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if (cart.isPresent()) {
            List<Product> products = cart.get().getProducts();
            products.removeIf(product -> product.getId().equals(productId));
            cart.get().setProducts(products);
            cartRepository.save(cart.get());
        }
    }

    public void removeAllProductsFromCart(Long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if(cart.isPresent()){
            cart.get().setProducts(new ArrayList<>());
            cartRepository.save(cart.get());
        }
    }

    public int getNoOfProductsInCart(Long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);

        if(cart.isPresent())
            return cart.get().getProducts().size();

        Cart newCart = provideCartServiceForUser(userId);
        return newCart.getProducts().size();
    }

}
