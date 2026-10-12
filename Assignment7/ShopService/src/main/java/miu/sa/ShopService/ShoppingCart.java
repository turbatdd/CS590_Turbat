package miu.sa.ShopService;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "carts")
public class ShoppingCart {
    @Id
    private String customerNumber;
    private List<CartItem> items = new ArrayList<>();

    public ShoppingCart() {}

    public String getCustomerNumber() { return customerNumber; }
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}