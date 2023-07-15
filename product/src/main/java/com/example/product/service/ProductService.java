package com.example.product.service;

import com.example.product.dao.daoImpl.ProductDaoImpl;
import com.example.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDaoImpl productDaoImpl;

    public List<Product> getAll(){
        return productDaoImpl.findAll();
    }

    public Product getById(int id){
        return productDaoImpl.findById(id);
    }

    public int addProduct(Product product){
        return productDaoImpl.addProduct(product);
    }

    public int deleteProduct(int id){
        return productDaoImpl.deleteProduct(id);
    }

    public int updateProduct(int id, Product product){
        return productDaoImpl.updateProduct(id,product);
    }
}
