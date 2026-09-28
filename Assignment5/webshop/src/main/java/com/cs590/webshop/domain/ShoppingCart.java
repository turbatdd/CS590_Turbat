package com.cs590.webshop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "shoppingcarts")
public class ShoppingCart {

    @Id
    private String customerNumber;
    private List<CartItem> items = new ArrayList<>();

    public ShoppingCart() {
    }

    public ShoppingCart(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {

        for (CartItem existing : items) {

            if (existing.getProductId()
                    .equals(item.getProductId())) {

                existing.setQuantity(
                        existing.getQuantity()
                                + item.getQuantity());

                return;
            }
        }

        items.add(item);
    }
}