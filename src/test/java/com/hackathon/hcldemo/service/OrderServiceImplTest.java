package com.hackathon.hcldemo.service;

import com.hackathon.hcldemo.dao.OrderDao;
import com.hackathon.hcldemo.model.OrderDetails;
import com.hackathon.hcldemo.model.OrderRequest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class OrderServiceImplTest {

    OrderDao orderDao = Mockito.mock(OrderDao.class);

    @InjectMocks
    OrderServiceImpl orderServiceImpl=new OrderServiceImpl();

    @Test(expected = NullPointerException.class)
    public void addOrderEntryTest() throws Exception {
        OrderRequest request = new OrderRequest();
        request.setOrderValue(30.0);
        request.setQuantity(5L);
        request.setTransactionType("BUY");
        request.setSymbol("TSLA");
        Mockito.when(orderDao.save(Mockito.mock(OrderDetails.class))).thenReturn(new OrderDetails());
        orderServiceImpl.addOrderEntry(request);
    }

}
