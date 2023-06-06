package com.example.demo.service;

import com.example.demo.dao.IStore;
import com.example.demo.dto.RequestStore;
import com.example.demo.dto.Response;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.repository.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService implements IStore {

    private MailService mailService ;
    Optional<Customer> customer ;

    @Autowired
    private CustomerRepo customerRepo ;
    @Autowired
    private StoreRepo storeRepo ;

    public StoreService(MailService mailService) {
        this.mailService = mailService;
    }



    @Override
    public Response createStore(RequestStore requestStore) {

        if(!customer.isEmpty()){
            if (requestStore.getCode_verification().equals(this.customer.get().getCode())){
                Store store = new Store().builder()
                        .name(requestStore.getName())
                        .customer(customer.get())
                        .build() ;

                storeRepo.save(store) ;
                customer.get().setRole("Customer");
                customerRepo.save(customer.get()) ;
                return new Response(200 , "created") ;
            }
            else {
                return new Response(500 , "code incorrect") ;
            }
        }
        else {
            return new Response(400 , "this is not exist you should to sign up") ;
        }
    }

    @Override
    public void sendEmailVerification(String email) {
        this.customer =  customerRepo.findByEmail(email) ;
        this.mailService.sendMail(email  , "email Verification", this.customer.get().getCode());
    }

    @Override
    public List<Product> getAllProducts(int id_Store) {
        Optional<Store> store = storeRepo.findById(id_Store) ;
        if(!store.isEmpty()){
            return store.get().getProductList();
        }
        return null ;
    }

    @Override
    public List<Order> getAllOrders(int id_Store) {
        Optional<Store> store = storeRepo.findById(id_Store) ;
        if(!store.isEmpty()){
            return store.get().getOrderList();
        }
        return null ;
    }

    @Override
    public int getNumberOffStore() {
        return this.storeRepo.findAll().size() ;
    }




}
