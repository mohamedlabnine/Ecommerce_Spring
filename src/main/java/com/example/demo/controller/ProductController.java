package com.example.demo.controller;

import com.example.demo.dao.IProductService;
import com.example.demo.dto.RequestProduct;
import com.example.demo.entity.Product ;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping(path = "/api/product/")
@ResponseBody
@AllArgsConstructor
public class ProductController {
    private IProductService iProductService ;

    @PostMapping("addProduct" )
    public Product addProduct(@RequestBody  RequestProduct product){
        return iProductService.addProduct(product) ;
    }

    @DeleteMapping("deleteProduct/{id}")
    public void deleteProduct(@PathVariable int id){
        iProductService.deleteProduct(id);
    }

    @PutMapping("editProduct")
    public Product editProduct(@RequestBody  Product product){
        return iProductService.editProduct(product) ;
    }

    @GetMapping("getAllProducts")
    public List<Product> getAllProduct(){
        return  iProductService.getAllProduct() ;
    }

    @GetMapping("getProduct/{id}")
    public Product findproduct(@PathVariable(name = "id") int id){
        return  iProductService.findProduct(id) ;
    }

    @GetMapping("getImage/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable int id){
        return  iProductService.downloadImage(id) ;
    }

    @PostMapping("uploadsImage")
    public void uploadsImage(@ModelAttribute MultipartFile file ){
        this.iProductService.uploadsImage(file);
    }

    @PostMapping("addProductToStore/{id_Store}")
    public void addProductToStore(@PathVariable int id_Store ,@RequestBody RequestProduct product ){
        this.iProductService.addProductToStore(id_Store , product);
    }

    @GetMapping("getByCategory/{category}")
    public List<Product> getByCategory(@PathVariable String category ){
        return  this.iProductService.getByCategory(category);
    }

}
