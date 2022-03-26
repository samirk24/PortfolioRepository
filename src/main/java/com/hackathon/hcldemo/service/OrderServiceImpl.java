package com.hackathon.hcldemo.service;

import com.hackathon.hcldemo.dao.OrderDao;
import com.hackathon.hcldemo.model.OrderDetails;
import com.hackathon.hcldemo.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    public OrderDetails addOrderEntry(OrderRequest request) throws Exception{
        OrderDetails details = new OrderDetails();
        details.setTransactionType(request.getTransactionType());
        details.setQuantity(request.getQuantity());
        details.setOrderValue(request.getOrderValue());
        details.setSymbol(request.getSymbol());
        details.setOrderDate(new Date());
        details.setPrice(request.getOrderValue()/request.getQuantity());
        details.setOrderStatus("SUBMITTED");
        return orderDao.save(details);
    }
}
