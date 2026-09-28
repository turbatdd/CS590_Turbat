package com.cs590.webshop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Document(collection = "products")
public class Product {

    @Id
    private final String productNumber;

    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Indexed
    @Field("supplier_id")
    private String supplierId;

    @Field("is_active")
    private boolean isActive;

    @Field("price")
    private BigDecimal price;

    @Field("stock_info")
    private StockInfo stockInfo;

    @Field("reviews")
    private final List<String> reviews;

    public Product(String productNumber, String name, String description, String supplierId, BigDecimal price, StockInfo stockInfo) {
        if (productNumber == null || productNumber.isBlank()) {
            throw new IllegalArgumentException("Product number cannot be empty.");
        }
        this.productNumber = productNumber;
        this.name = name;
        this.description = description;
        this.supplierId = supplierId;
        this.price = price;
        this.stockInfo = stockInfo;
        this.isActive = true;
        this.reviews = new ArrayList<>();
    }

    public void decreaseStock(int quantity) {
        if (this.stockInfo != null) {
            this.stockInfo = this.stockInfo.reserve(quantity);
        }
    }

    public void receiveRestock(int quantity) {
        if (this.stockInfo != null) {
            this.stockInfo = this.stockInfo.addStock(quantity);
        }
    }

    public void addReview(String review) {
        if (review != null && !review.isBlank()) {
            this.reviews.add(review);
        }
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public boolean isActive() {
        return isActive;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public StockInfo getStockInfo() {
        return stockInfo;
    }

    public List<String> getReviews() {
        return Collections.unmodifiableList(reviews);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productNumber, product.productNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNumber);
    }
}