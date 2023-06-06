package com.example.demo.dao;

import com.example.demo.dto.RequestProduct;
import com.example.demo.entity.Product;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface IProductService {
    public Product addProduct(RequestProduct product) ;
    public void deleteProduct(int id) ;
    public Product editProduct(Product product) ;
    public List<Product> getAllProduct() ;
    public Product findProduct(int id) ;
    public void uploadsImage(MultipartFile file) ;
    public ResponseEntity<Resource> downloadImage(int id) ;

    void addProductToStore(int id_store, RequestProduct product);

    List<Product> getByCategory(String category);
}
