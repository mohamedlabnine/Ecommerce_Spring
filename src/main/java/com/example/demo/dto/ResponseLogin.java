package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseLogin {
    private int id ;
    private String jwt ;
    private String role ;
    private int status ;
    private int id_store ;
}
