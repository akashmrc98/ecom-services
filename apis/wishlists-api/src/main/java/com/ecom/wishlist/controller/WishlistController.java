package com.ecom.wishlist.controller;

import com.ecom.wishlist.domain.Product;
import com.ecom.wishlist.dto.RegisterDto;
import com.ecom.wishlist.service.ProductService;
import com.ecom.wishlist.service.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;
    private final ProductService productService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createWishlistServiceForUser(@RequestBody RegisterDto registerDto) {
        wishlistService.provideWishListServiceForUser(registerDto);
    }

    @GetMapping("/{userId}/size")
    public ResponseEntity<Integer> getSizeOfProducts(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(
                wishlistService.getSizeOfProducts(userId),
                HttpStatus.OK
        );
    }

    @PostMapping("/{userId}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductToWishlistByID(
            @RequestBody Product product,
            @PathVariable("userId") Long userId
    ) {
        productService.saveProduct(product);
        wishlistService.addProductToWishList(product, userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Iterable<Product>> getWishlistByID(@PathVariable("userId") Long userId) {
        Iterable<Product> products = wishlistService.getWishlistByUserId(userId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/products/{productId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeProductFromWishlistByProductID(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId) {
        wishlistService.removeProductFromWishList(userId, productId);
    }


}
