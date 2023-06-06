package com.example.demo.service;

import com.example.demo.dao.IProductService;
import com.example.demo.dto.RequestProduct;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo ;
    @Autowired
    private OrderRepo orderRepo ;

    @Autowired
    private StoreRepo storeRepo ;
    private String location = "./src/main/resources/image/" ;


    @Override
    public Product addProduct(RequestProduct product) {
        // create object of product using RequestProduct class sending by frontend
        Product product1 = new Product().builder()
                .name(product.getName())
                .price(product.getPrice())
                .image(product.getImage())
                .offer(product.getOffer())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .build();
        // save Product and send de Response
        return productRepo.save(product1) ;

    }

    @Override
    public void addProductToStore(int id, RequestProduct product) {
        Product product1 = this.addProduct(product) ;
        Optional<Store> store = storeRepo.findById(id) ;
        if(!store.isEmpty()){
            store.get().getProductList().add(product1) ;
            storeRepo.save(store.get()) ;
        }
    }

    @Override
    public List<Product> getByCategory(String category) {
       return this.productRepo.findByCategory(category);
    }

    @Override
    public void deleteProduct(int id) {
        List<Order> orders = this.orderRepo.findAll() ;
        Product product = this.findProduct(id) ;
        orders.forEach(order -> {
            order.getProducts().remove(product);
            orderRepo.save(order) ;
        });
        productRepo.delete(product);
    }

    @Override
    public Product editProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product findProduct(int id) {
        Optional<Product> product = productRepo.findById(id) ;
        return product.orElse(new Product());
    }

    @Override
    public void uploadsImage(MultipartFile file)  {
        String path =  location + file.getOriginalFilename() ;
        try {
            Files.copy(file.getInputStream() , Paths.get(path)) ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResponseEntity<Resource> downloadImage(int id) {

        Product product = this.findProduct(id) ;

        Path path = Paths.get(location).resolve(product.getImage()) ;
        try {
            Resource file = new UrlResource(path.toUri()) ;
            Path path1 = file.getFile().toPath();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path1))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
