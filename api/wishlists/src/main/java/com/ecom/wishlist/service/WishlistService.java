package com.ecom.wishlist.service;

import com.ecom.wishlist.client.ProductsClient;
import com.ecom.wishlist.domain.Product;
import com.ecom.wishlist.domain.Wishlist;
import com.ecom.wishlist.dto.WishListProductsMapDto;
import com.ecom.wishlist.repository.WishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductsClient productsClient;

    public Wishlist provideWishListServiceForUser(Long userId){
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(userId);
        wishlist.setProducts(new ArrayList<>());
        wishlistRepository.save(wishlist);
        return wishlist;
    }

    public Integer getSizeOfProducts(Long userId){
        Optional<Wishlist> wishlist = wishlistRepository.findByUserId(userId);

        if(wishlist.isPresent())
            return wishlist.get().getProducts().size();

        Wishlist newWishList = provideWishListServiceForUser(userId);
        return newWishList.getProducts().size();
    }

    public void addProductToWishList(Product product, Long userId) {
        Optional<Wishlist> wishlist = wishlistRepository.findByUserId(userId);

        if(wishlist.isPresent()){
            List<Product> products = wishlist.get().getProducts();
            products.add(product);
            wishlist.get().setProducts(products);
            wishlistRepository.save(wishlist.get());
        }

        if (wishlist.isEmpty()) {
            Wishlist newWishlist = provideWishListServiceForUser(userId);
            List<Product> products = new ArrayList<>();
            products.add(product);
            newWishlist.setProducts(products);
            wishlistRepository.save(newWishlist);
        }

    }

    public WishListProductsMapDto getWishlistByUserId(Long userId) {
        Optional<Wishlist> wishlist = wishlistRepository.findByUserId(userId);
        if (wishlist.isPresent()){
            Iterable<Product> products = wishlist.get().getProducts();
            return productsClient.getCartProducts(products);
        }
        return new WishListProductsMapDto();
    }

    public void removeProductFromWishList(Long userId, Long productId) {
        Optional<Wishlist> wishlist = wishlistRepository.findByUserId(userId);
        if(wishlist.isPresent()){
            List<Product> products = wishlist.get().getProducts();
            products.removeIf(product -> product.getId().equals(productId));
            wishlist.get().setProducts(products);
            wishlistRepository.save(wishlist.get());
        }
    }

    public void removeAllProductsFromCart(Long userId){
        Optional<Wishlist> wishlist = wishlistRepository.findByUserId(userId);
        List<Product> products = new ArrayList<>();
        if(wishlist.isPresent()){
            wishlist.get().setProducts(products);
            wishlistRepository.save(wishlist.get());
        }
    }

}
