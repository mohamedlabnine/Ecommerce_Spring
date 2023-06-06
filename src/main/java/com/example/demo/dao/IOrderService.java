package com.example.demo.dao;


import com.example.demo.dto.OrderRequest;
import com.example.demo.entity.Order;

import java.util.List;
import com.example.demo.entity.Product ;

public interface IOrderService {
    public List<Order> getAllOrders() ;
    public  Order getOrder(int id_Order) ;
    public Order saveOrder(OrderRequest order) ;
}
