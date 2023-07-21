package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getproduct")
    public List<Product> getProduct(){
        return productService.getAll();
    }

    @GetMapping("/getproductbyid/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getById(id);
    }

    @PostMapping("/addproduct")
    public int addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/deleteproduct/{id}")
    public int deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    @PutMapping("/updateproduct/{id}")
    public int updateProduct(@PathVariable int id,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }
}
