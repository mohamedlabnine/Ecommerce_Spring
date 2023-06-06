package com.example.demo.controller;

import com.example.demo.dao.ICustomer;
import com.example.demo.dto.Login;
import com.example.demo.dto.Response;
import com.example.demo.dto.ResponseLogin;
import com.example.demo.entity.Customer;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth/")
@ResponseBody
@AllArgsConstructor
public class AuthController {
    private ICustomer iCustomer ;

    @PostMapping("login")
    public ResponseLogin login(@RequestBody  Login login){
        return iCustomer.login(login) ;
    }


    @PostMapping("register")
    public Response register(@RequestBody Customer customer){
        return iCustomer.register(customer) ;
    }


    @GetMapping("NumberOfCustomers")
    public int getNumberOfCustomers(){
        return  iCustomer.getNumberOfCustomer() ;
    }
}
