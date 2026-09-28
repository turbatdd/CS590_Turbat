package com.cs590.webshop.repository;

import com.cs590.webshop.domain.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ShoppingCartRepository
        extends MongoRepository<ShoppingCart, String> {
}