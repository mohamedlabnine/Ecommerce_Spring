package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Register {
    private String firstname ;
    private String lastname ;
    private String email ;
    private String password ;
    private String role ;
}
