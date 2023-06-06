package com.example.demo.service;

import com.example.demo.dao.ICustomer;
import com.example.demo.dto.AppUserDetails;
import com.example.demo.dto.Login;
import com.example.demo.dto.Response;
import com.example.demo.dto.ResponseLogin;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Store;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.repository.StoreRepo;
import com.example.demo.security.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService implements ICustomer {
    private PasswordEncoder passwordEncoder ;
    private AuthenticationManager authenticationManager ;
    private JwtTokenUtil jwtTokenUtil ;
    private CustomerService customerService ;
    private final StoreRepo storeRepo;
    private final CustomerRepo customerRepo;

    @Override
    public ResponseLogin login(Login login) {
        AppUserDetails userDetails = (AppUserDetails) customerService.loadUserByUsername(login.getEmail());
        int id_store = this.getStoreOfCustomer(userDetails.getID()) != null ? this.getStoreOfCustomer(userDetails.getID()).getId() : 0 ;
        if(userDetails != null && passwordEncoder.matches(login.getPassword() , userDetails.getPassword())){
            Authentication a = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(a);
            return new ResponseLogin(userDetails.getID() ,jwtTokenUtil.generateToken(userDetails), userDetails.getAuthorities().toString(),200 , id_store);
        }
        else {
            return new ResponseLogin(0 , "email or password incorrect " , "plz sign up",404 , 0);
        }
    }

    @Override
    public Response register(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        try{
            customerService.getCustomerRepo().save(customer);
            return new Response(201,"created") ;
        }catch (Exception e){
            return new Response(500,"this has already exist") ;
        }
    }

    @Override
    public int getNumberOfCustomer() {
        return customerService.getCustomerRepo().findAll().size() ;
    }

    public Store getStoreOfCustomer(int id){
        Optional<Customer> customer = customerRepo.findById(id) ;
        Optional<Store> store = storeRepo.findByCustomer(customer.get()) ;
        return store.orElse(null) ;
    }
}
