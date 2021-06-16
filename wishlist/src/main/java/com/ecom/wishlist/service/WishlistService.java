package com.ecom.wishlist.service;

import com.ecom.wishlist.domain.Product;
import com.ecom.wishlist.domain.Wishlist;
import com.ecom.wishlist.dto.RegisterDto;
import com.ecom.wishlist.repository.WishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public void provideWishListServiceForUser(RegisterDto registerDto){
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(registerDto.getUserId());
        wishlistRepository.save(wishlist);
    }


    public void addProductToWishList(Product product, Long userId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId);
        List<Product> products = wishlist.getProducts();
        products.add(product);
        wishlist.setProducts(products);
        wishlistRepository.save(wishlist);
    }

    public Iterable<Product> getWishlistByUserId(Long userId) {
        return wishlistRepository.findByUserId(userId).getProducts();
    }

    public void removeProductFromWishList(Long userId, Long productId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId);
        List<Product> products = wishlist.getProducts();
        products.removeIf(product -> product.getProductId().equals(productId));
        wishlist.setProducts(products);
        wishlistRepository.save(wishlist);
    }

    public void removeAllProductsFromCart(Long userId){
        Wishlist wishlist = wishlistRepository.findByUserId(userId);
        List<Product> products = new ArrayList<>();
        wishlist.setProducts(products);
        wishlistRepository.save(wishlist);
    }

}
