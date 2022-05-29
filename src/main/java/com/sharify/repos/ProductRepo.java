package com.sharify.repos;

import com.sharify.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findByCategory(String category);
    List<Product> findAll();
    Product getOne(Long id);
}
