package com.example.orders.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateStockDto {
    private Long[] productIdList;
    private int[] quantityList;
}
