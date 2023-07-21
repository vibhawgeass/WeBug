package com.example.product.dao;

import com.example.product.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();
    Product findById(int id);
    int addProduct(Product product);
    int deleteProduct(int id);
    int updateProduct(int id, Product product);
}
