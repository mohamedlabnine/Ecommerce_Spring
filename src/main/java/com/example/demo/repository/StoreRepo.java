package com.example.demo.repository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepo extends JpaRepository<Store, Integer> {
    Optional<Store> findByCustomer(Customer customer) ;
}
