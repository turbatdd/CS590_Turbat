package com.cs590.webshop.service;

import com.cs590.webshop.dto.CartItemDto;
import com.cs590.webshop.dto.ProductDto;
import com.cs590.webshop.dto.ShoppingCartDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Service
public class WebShopRestService {

    private final RestClient restClient;

    public WebShopRestService(RestClient restClient) {
        this.restClient = restClient;
    }

    public void runDemo() {
        String productNumber = "P-101";
        String customerId = "CUST-001";

        System.out.println("Adding New Product ===");
        ProductDto newProduct = new ProductDto();
        newProduct.setProductNumber(productNumber);
        newProduct.setName("Wireless Gaming Mouse");
        newProduct.setDescription("Ergonomic RGB optical wireless mouse");
        newProduct.setSupplierId("SUP-99");
        newProduct.setActive(true);
        newProduct.setPrice(new BigDecimal("79.99"));
        newProduct.setQuantityInStock(50);
        newProduct.setWarehouseLocationCode("WH-A1");

        ProductDto createdProduct = restClient.post()
                .uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .body(newProduct)
                .retrieve()
                .body(ProductDto.class);

        System.out.println("Product successfully created");
        System.out.println("\nFetching Product ===");

        ProductDto fetchedProduct = restClient.get()
                .uri("/products/{productNumber}", productNumber)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ProductDto.class);

        if (fetchedProduct != null) {
            System.out.println("Fetched Product Details:");
            System.out.println("  ID          : " + fetchedProduct.getProductNumber());
            System.out.println("  Name        : " + fetchedProduct.getName());
            System.out.println("  Description : " + fetchedProduct.getDescription());
            System.out.println("  Price       : " + fetchedProduct.getPrice());
            System.out.println("  Stock       : " + fetchedProduct.getQuantityInStock());
        }

        System.out.println("\nAdding Product to Shopping Cart ===");
        CartItemDto cartItemRequest = new CartItemDto();
        cartItemRequest.setProductId(productNumber);
        cartItemRequest.setQuantity(2);

        ShoppingCartDto updatedCart = restClient.post()
                .uri("/carts/{customerId}/items", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cartItemRequest)
                .retrieve()
                .body(ShoppingCartDto.class);

        System.out.println("Item added to cart successfully!");

        // -------------------------------------------------------------------
        // 4. Get the shopping cart and print to console
        // -------------------------------------------------------------------
        System.out.println("\nFetching Shopping Cart ===");
        ShoppingCartDto fetchedCart = restClient.get()
                .uri("/carts/{customerId}", customerId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ShoppingCartDto.class);

        System.out.println("Shopping Cart Details:");
        if (fetchedCart != null) {
            System.out.println("  Customer ID : " + fetchedCart.getCustomerNumber());
            System.out.println("  Items Count : " + (fetchedCart.getItems() != null ? fetchedCart.getItems().size() : 0));

            if (fetchedCart.getItems() != null) {
                fetchedCart.getItems().forEach(item ->
                        System.out.println("    -> Product: " + item.getProductId() + " | Quantity: " + item.getQuantity())
                );
            }
        }
    }
}
