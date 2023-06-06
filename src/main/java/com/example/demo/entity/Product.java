package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "name" , unique = true)
    private String name ;
    private String description ;
    private String category ;
    private int offer ;
    private int quantity ;
    private int price ;
    private String image ;

    public Product(String name, String description, String category, int offer, int quantity, int price, String image) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.offer = offer;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }
}
