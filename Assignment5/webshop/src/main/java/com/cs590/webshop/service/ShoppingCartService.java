package com.cs590.webshop.service;

import com.cs590.webshop.domain.CartItem;
import com.cs590.webshop.domain.ShoppingCart;
import com.cs590.webshop.dto.CartItemDto;
import com.cs590.webshop.dto.ShoppingCartDto;
import com.cs590.webshop.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository repository;

    public ShoppingCartService(
            ShoppingCartRepository repository) {

        this.repository = repository;
    }

    public ShoppingCartDto addToCart(
            String customerNumber,
            CartItemDto dto) {

        ShoppingCart cart = repository
                .findById(customerNumber)
                .orElse(new ShoppingCart(customerNumber));

        CartItem item = new CartItem(
                dto.getCartId(),
                dto.getCustomerId(),
                dto.getProductId(),
                dto.getQuantity()
        );

        cart.addItem(item);

        ShoppingCart saved = repository.save(cart);

        return toDto(saved);
    }

    public ShoppingCartDto getCart(
            String customerNumber) {

        ShoppingCart cart = repository
                .findById(customerNumber)
                .orElse(new ShoppingCart(customerNumber));

        return toDto(cart);
    }

    private ShoppingCartDto toDto(
            ShoppingCart cart) {

        return new ShoppingCartDto(
                cart.getCustomerNumber(),
                cart.getItems()
                        .stream()
                        .map(item ->
                                new CartItemDto(
                                        item.getCartId(),
                                        item.getCustomerId(),
                                        item.getProductId(),
                                        item.getQuantity()
                                ))
                        .collect(Collectors.toList())
        );
    }
}