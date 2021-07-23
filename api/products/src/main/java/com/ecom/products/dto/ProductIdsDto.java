package com.ecom.products.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductIdsDto {
    private Iterable<ProductIdDto> productIdDtoList;
}