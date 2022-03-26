package com.hackathon.hcldemo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ORDER_DETAILS")
@Data
public class OrderDetails {

    @Id
    @Column(name = "ORDER_REF_NO", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderRefNo;

    @Column(name = "ORDER_DATE", nullable = false)
    private Date orderDate;

    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "ORDER_VALUE", nullable = false)
    private Double orderValue;

    @Column(name = "ORDER_STATUS", nullable = false)
    private String orderStatus;

    @Column(name = "TRANSACTION_TYPE", nullable = false)
    private String transactionType;

    @Column(name = "SYMBOL", nullable = false)
    private String symbol;

}
