package com.ecom.products.dto;

import com.ecom.products.domain.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductInDetailDto {
    private Long id;
    private String description;
    private String brand;
    private Double price;
    private Integer stock;
    private String[] specifications;
    private List<Image> images;
}
