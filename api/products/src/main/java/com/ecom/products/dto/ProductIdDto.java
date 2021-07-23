package com.ecom.products.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductIdDto {
    private Long id;
    private int quantity;
}
