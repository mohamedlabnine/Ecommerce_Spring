package com.example.demo.controller;

import com.example.demo.dto.RequestStore;
import com.example.demo.dto.Response;
import com.example.demo.entity.Order;
import com.example.demo.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Product;

import java.util.List;

@RestController
@RequestMapping(path = "/api/store/")
@ResponseBody
@AllArgsConstructor
public class StoreController {
    private StoreService iStore ;

    @PostMapping("createStore" )
    public Response createStore(@RequestBody RequestStore requestStore){
        return this.iStore.createStore(requestStore);
    }

    @GetMapping("SendEmailVerification/{email}")
    public void SendEmailVerification(@PathVariable String email){
        this.iStore.sendEmailVerification(email); ;
    }

    @GetMapping("getAllProducts/{id_Store}")
    public List<Product> SendEmailVerification(@PathVariable int id_Store){
        return this.iStore.getAllProducts(id_Store);
    }


    @GetMapping("getAllOrders/{id_Store}")
    public List<Order> getAllOrders(@PathVariable  int id_Store) {
        return this.iStore.getAllOrders(id_Store) ;
    }

    @GetMapping("getNumberOffStore")
    public int getNumberOffStore() {
        return this.iStore.getNumberOffStore() ;
    }
}
