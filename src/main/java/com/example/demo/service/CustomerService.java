package com.example.demo.service;


import com.example.demo.dto.AppUserDetails;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Data
public class CustomerService implements UserDetailsService {
    @Autowired
    private CustomerRepo customerRepo ;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> appUser = customerRepo.findByEmail(email);
        if(!appUser.isEmpty()){
            return new AppUserDetails(appUser.get()) ;
        }
        return  null ;
    }

}
