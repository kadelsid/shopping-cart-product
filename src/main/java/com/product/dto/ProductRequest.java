package com.product.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private String productName;
    private String category;
    private double unitPrice;
    private int quantityAvailable;
}
