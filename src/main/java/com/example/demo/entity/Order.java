package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "operation")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @ManyToOne
    private Customer customer ;
    private Date date ;
    private double amount ;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products ;

    public Order(Customer customer, Date date, double amount, List<Product> products) {
        this.customer = customer;
        this.date = date;
        this.amount = amount;
        this.products = products;
    }
}
