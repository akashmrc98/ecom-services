package com.ecom.products.controller;

import com.ecom.products.dto.ProductInDetailDto;
import com.ecom.products.dto.ProductsDto;
import com.ecom.products.dto.ReviewRatingsDto;
import com.ecom.products.dto.UpdateStockDto;
import com.ecom.products.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/count")
    public ResponseEntity<Long> getProductsCount() {
        return new ResponseEntity<Long>(productService.getProductsCount(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductInDetailDto> getProductsCount(@PathVariable("productId") Long productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @PostMapping("/update-stock")
    public void updateStock(@RequestBody UpdateStockDto updateStockDto) {
        productService.updateStock(updateStockDto);
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<ProductsDto>>
    getProducts(
            @RequestParam(defaultValue = "0", value = "pageNo") Integer pageNo,
            @RequestParam(defaultValue = "4", value = "pageSize") Integer pageSize,
            @RequestParam(defaultValue = "id", value = "sortBy") String sortBy
    ) {
        return new ResponseEntity<Iterable<ProductsDto>>
                (productService.getProducts(pageNo, pageSize, sortBy), HttpStatus.OK);
    }

    @PostMapping("/reviews/{productId}")
    public void updateProductReviewRatings(
            @RequestBody ReviewRatingsDto reviewRatingsDto,
            @PathVariable("productId") Long productId
    ) {
        productService.updateReviewRatings(reviewRatingsDto, productId);
    }

    @PostMapping("/ids")
    public ResponseEntity<Iterable<ProductsDto>> sendProducts(@RequestBody List<Long> productIds){
        return new ResponseEntity<>(productService.sendProducts(productIds), HttpStatus.OK);
    }
}
