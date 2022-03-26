package com.hackathon.hcldemo.model;

import lombok.Data;

@Data
public class Asset {
    private String symbol;
    private String securityName;
    private String price;
    private String quantity;
    private String description;
}
