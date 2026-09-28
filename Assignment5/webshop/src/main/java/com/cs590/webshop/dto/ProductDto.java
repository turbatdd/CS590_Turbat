package com.cs590.webshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDto {

    private String productNumber;
    private String name;
    private String description;
    private String supplierId;
    private boolean active;

    private BigDecimal price;

    private int quantityInStock;
    private String warehouseLocationCode;

    private List<String> reviews = new ArrayList<>();

    public ProductDto() {
    }

    public ProductDto(String productNumber, String name, String description, String supplierId,
                      boolean active, BigDecimal price, String currency,
                      int quantityInStock, String warehouseLocationCode, List<String> reviews) {
        this.productNumber = productNumber;
        this.name = name;
        this.description = description;
        this.supplierId = supplierId;
        this.active = active;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.warehouseLocationCode = warehouseLocationCode;
        this.reviews = reviews != null ? reviews : new ArrayList<>();
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @JsonProperty("active")
    public boolean isActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(boolean active) {
        this.active = active;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getWarehouseLocationCode() {
        return warehouseLocationCode;
    }

    public void setWarehouseLocationCode(String warehouseLocationCode) {
        this.warehouseLocationCode = warehouseLocationCode;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }
}