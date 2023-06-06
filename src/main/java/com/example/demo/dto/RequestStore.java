package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RequestStore {
    private String name ;
    private String email ;
    private String code_verification ;
}
