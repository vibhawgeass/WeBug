package com.example.product.dao.daoImpl;

import com.example.product.dao.ProductDao;
import com.example.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Product> findAll() {
        String sql="select * from product";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper(Product.class));
    }

    @Override
    public Product findById(int id) {
        String sql="select * from product where product_id=?";
        return (Product)jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Product.class));
    }

    @Override
    public int addProduct(Product product) {
        String sql="INSERT INTO product(product) VALUES (?)";
        return jdbcTemplate.update(sql,product.getProduct());

    }

    @Override
    public int deleteProduct(int id) {
        String sql="delete from product where product_id=?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateProduct(int id, Product product) {
        String sql="update product set product=? where product_id=? ";
        return jdbcTemplate.update(sql,product.getProduct());

    }
}
