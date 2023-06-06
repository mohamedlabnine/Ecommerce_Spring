package com.example.demo.dao;

import com.example.demo.dto.Login;
import com.example.demo.dto.Register;
import com.example.demo.dto.Response;
import com.example.demo.dto.ResponseLogin;
import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ICustomer  {
    ResponseLogin login(Login login) ;
    Response register(Customer customer) ;
    int getNumberOfCustomer() ;
}
