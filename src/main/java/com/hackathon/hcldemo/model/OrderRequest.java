package com.hackathon.hcldemo.model;

import lombok.Data;

@Data
public class OrderRequest {
    private String transactionType;
    private Long quantity;
    private Double orderValue;
    private String symbol;
}
