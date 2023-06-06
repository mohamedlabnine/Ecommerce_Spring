package com.example.demo.dao;

import com.example.demo.dto.RequestStore;
import com.example.demo.dto.Response;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;

import java.util.List;

public interface IStore  {
    Response createStore(RequestStore store) ;
    void sendEmailVerification(String email) ;
    List<Product> getAllProducts(int id_Store) ;
    List<Order> getAllOrders(int id_Store) ;
    int getNumberOffStore() ;
}
