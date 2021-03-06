package com.ecom.cart.controller;

import com.ecom.cart.domain.Product;
import com.ecom.cart.dto.UpdateProductQuantityDto;
import com.ecom.cart.dto.CartProductsMapDto;
import com.ecom.cart.service.CartService;
import com.ecom.cart.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/carts")
@CrossOrigin
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/{userId}/size")
    public ResponseEntity<Integer> getSizeOfProducts(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(
                cartService.getNoOfProductsInCart(userId),
                HttpStatus.OK
        );
    }

    @PostMapping("/{userId}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductToCartByID(
            @PathVariable("userId") Long userId,
            @RequestBody Product product
    ) {
        productService.saveProduct(product);
        cartService.addProductToCart(product, userId);
    }

    @PutMapping("{userId}/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProductQuantityInCart(
            @RequestBody UpdateProductQuantityDto updateProductQuantityDto,
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId
    ) {
        cartService.updateProductQuantityInCart(userId, productId, updateProductQuantityDto.getProductQuantity());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartProductsMapDto> getCartByID(@PathVariable("userId") Long userId) {
        CartProductsMapDto products = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("{userId}/products/{productId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeProductFromCartByProductID(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId) {
        cartService.removeProductFromCart(userId, productId);
        productService.removeProduct(productId);
    }

    @DeleteMapping("/{userId}/products")
    @ResponseStatus(HttpStatus.OK)
    public void clearCart(@PathVariable("userId") Long userId) {
        cartService.removeAllProductsFromCart(userId);
    }

}
