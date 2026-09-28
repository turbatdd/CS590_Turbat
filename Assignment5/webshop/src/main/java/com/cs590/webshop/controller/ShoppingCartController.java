package com.cs590.webshop.controller;

import com.cs590.webshop.dto.CartItemDto;
import com.cs590.webshop.dto.ShoppingCartDto;
import com.cs590.webshop.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class ShoppingCartController {

    private final ShoppingCartService service;

    public ShoppingCartController(
            ShoppingCartService service) {

        this.service = service;
    }

    @PostMapping("/{customerNumber}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingCartDto addToShoppingCart(
            @PathVariable String customerNumber,
            @RequestBody CartItemDto item) {

        return service.addToCart(
                customerNumber,
                item);
    }

    @GetMapping("/{customerNumber}")
    public ShoppingCartDto getShoppingCart(
            @PathVariable String customerNumber) {

        return service.getCart(customerNumber);
    }
}