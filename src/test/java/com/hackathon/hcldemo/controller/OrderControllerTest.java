package com.hackathon.hcldemo.controller;


import com.hackathon.hcldemo.model.OrderDetails;
import com.hackathon.hcldemo.model.OrderRequest;
import com.hackathon.hcldemo.service.OrderService;
import com.hackathon.hcldemo.service.OrderServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class OrderControllerTest {

    OrderController controller=new OrderController();

    OrderService orderService=Mockito.mock(OrderServiceImpl.class);

    @Test
    public void testAddOrderEntry() throws Exception {
        OrderRequest request = new OrderRequest();
        request.setOrderValue(30.0);
        request.setQuantity(5L);
        request.setTransactionType("BUY");
        request.setSymbol("TSLA");
        Mockito.when(orderService.addOrderEntry(request)).thenReturn(new OrderDetails());
        controller.addOrderEntry(request);
    }

    @Test
    public void testAddOrderEntryValidation(){
        OrderRequest request = new OrderRequest();
        request.setOrderValue(-1.0);
        request.setQuantity(-1L);
        request.setTransactionType("BUY123");
        request.setSymbol(null);
        controller.addOrderEntry(request);
    }

    @Test
    public void testAddOrderEntryForNullRequest(){
        OrderRequest request = Mockito.mock(OrderRequest.class);
        controller.addOrderEntry(request);
    }


}
