package com.example.demo.controller;

import com.example.demo.dto.OrderRequest;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepo;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/order/")
@ResponseBody
@AllArgsConstructor
public class OrderController {

    private OrderService orderService ;

    @GetMapping("getAllOrders")
    public List<Order> getAllOrders(){
        return  orderService.getAllOrders() ;
    }

    @GetMapping("getOrder/{order_id}")
    public Order getOrder(@PathVariable  int order_id){
        return  orderService.getOrder(order_id) ;
    }

    @PostMapping("saveOrder")
    public Order saveOrder(@RequestBody OrderRequest orderRequest){

        return this.orderService.saveOrder(orderRequest);
    }
}
