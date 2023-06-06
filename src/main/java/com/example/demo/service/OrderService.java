package com.example.demo.service;

import com.example.demo.dao.IOrderService;
import com.example.demo.dto.OrderRequest;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepo orderRepo ;

    @Autowired
    private CustomerRepo customerRepo ;
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll() ;
    }

    @Override
    public Order getOrder(int id_Order) {
        Optional<Order> order = orderRepo.findById(id_Order) ;
        return order.orElse(new Order()) ;
    }

    @Override
    public Order saveOrder(OrderRequest orderRequest) {
        Optional<Customer> customer = customerRepo.findById(orderRequest.getId_customer()) ;
        List<Product> products = new ArrayList<>();

        orderRequest.getList_product_id().forEach(  id -> ListProductToOrder( id , products) );

        Order order = Order.builder()
                .date(orderRequest.getDate())
                .customer(customer.get())
                .amount(orderRequest.getAmount())
                .products(products)
                .build();

        return orderRepo.save(order) ;

    }

    public void ListProductToOrder(int id , List<Product> products){
        Optional<Product> product = productRepo.findById(id) ;
        products.add(product.get()) ;
    }
}
