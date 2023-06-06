package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RequestProduct {
    private String name ;
    private String description ;
    private String category ;
    private int offer ;
    private int quantity ;
    private int price ;
    private String image ;
}
