package com.cs590.webshop.dto;

import java.util.List;

public class ShoppingCartDto {

    private String customerNumber;
    private List<CartItemDto> items;

    public ShoppingCartDto() {
    }

    public ShoppingCartDto(String customerNumber,
                           List<CartItemDto> items) {
        this.customerNumber = customerNumber;
        this.items = items;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public List<CartItemDto> getItems() {
        return items;
    }
}