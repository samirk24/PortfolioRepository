package com.hackathon.hcldemo.dao;

import com.hackathon.hcldemo.model.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends CrudRepository<OrderDetails, Long> {
}
