package com.example.orders.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UpdateStockDto implements Serializable {
    private Long[] productIdList;
    private int[] quantityList;
}
