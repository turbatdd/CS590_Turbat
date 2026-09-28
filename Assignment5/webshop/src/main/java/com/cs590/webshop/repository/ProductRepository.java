package com.cs590.webshop.repository;

import com.cs590.webshop.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository
        extends MongoRepository<Product, String> {
}