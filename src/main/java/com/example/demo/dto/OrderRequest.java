package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
public class OrderRequest {
    private int id_customer ;
    private double amount ;
    private Date date ;
    private List<Integer> list_product_id ;
}
