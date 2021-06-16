package com.ecom.cart.controller;

import com.ecom.cart.domain.Product;
import com.ecom.cart.dto.RegisterDto;
import com.ecom.cart.service.CartService;
import com.ecom.cart.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @PostMapping("/size/{userId}")
    public ResponseEntity<Integer> getNoOfProductsCart(@PathVariable("userId") Long userId){
        return new ResponseEntity<Integer>(cartService.getNoOfProductsInCart(userId), HttpStatus.OK);
    }

    @PostMapping("/")
    public void saveCartServiceForUser(@RequestBody RegisterDto registerDto) {
        cartService.provideCartServiceForUser(registerDto);
    }

    @PostMapping("/{userId}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductToCartByID(
            @RequestBody Product product,
            @PathVariable("userId") Long userId
    ) {
        productService.saveProduct(product);
        cartService.addProductToCart(product, userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Iterable<Product>> getCartByID(@PathVariable("userId") Long userId) {
        Iterable<Product> products = cartService.getCartByUserId(userId);
        return new ResponseEntity<Iterable<Product>>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/products/{productId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeProductFromCartByProductID(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId) {
        cartService.removeProductFromCart(userId , productId);
        productService.removeProduct(productId);
    }

}
