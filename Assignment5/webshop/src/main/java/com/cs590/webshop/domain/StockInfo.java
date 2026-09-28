package com.cs590.webshop.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

public final class StockInfo {

    @Field("quantity_in_stock")
    private final int quantityInStock;

    @Field("warehouse_location_code")
    private final String warehouseLocationCode;

    public StockInfo(int quantityInStock, String warehouseLocationCode) {
        if (quantityInStock < 0) {
            throw new IllegalArgumentException("Quantity in stock cannot be negative.");
        }
        this.quantityInStock = quantityInStock;
        this.warehouseLocationCode = warehouseLocationCode;
    }

    public StockInfo reserve(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity to reserve must be greater than zero.");
        }
        if (this.quantityInStock < quantity) {
            throw new IllegalStateException("Insufficient stock available to reserve.");
        }
        return new StockInfo(this.quantityInStock - quantity, this.warehouseLocationCode);
    }

    public StockInfo addStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity to add must be greater than zero.");
        }
        return new StockInfo(this.quantityInStock + quantity, this.warehouseLocationCode);
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public String getWarehouseLocationCode() {
        return warehouseLocationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockInfo stockInfo = (StockInfo) o;
        return quantityInStock == stockInfo.quantityInStock &&
                Objects.equals(warehouseLocationCode, stockInfo.warehouseLocationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantityInStock, warehouseLocationCode);
    }
}