package com.hackathon.hcldemo.service;

import com.hackathon.hcldemo.model.OrderDetails;
import com.hackathon.hcldemo.model.OrderRequest;

public interface OrderService {
    OrderDetails addOrderEntry(OrderRequest request) throws Exception;
}
