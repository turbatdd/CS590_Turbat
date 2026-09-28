package com.cs590.webshop.service;

import com.cs590.webshop.domain.Product;
import com.cs590.webshop.domain.StockInfo;
import com.cs590.webshop.dto.ProductDto;
import com.cs590.webshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductDto addProduct(ProductDto dto) {
        // Map DTO primitive/nested values to domain Value Objects
        StockInfo stockInfo = new StockInfo(dto.getQuantityInStock(), dto.getWarehouseLocationCode());

        Product product = new Product(
                dto.getProductNumber(),
                dto.getName(),
                dto.getDescription(),
                dto.getSupplierId(),
                dto.getPrice(),
                stockInfo
        );

        Product saved = repository.save(product);

        return toDto(saved);
    }

    public ProductDto getById(String id) {
        Product product = repository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found: " + id));

        return toDto(product);
    }

    private ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setProductNumber(product.getProductNumber());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setSupplierId(product.getSupplierId());
        dto.setActive(product.isActive());

        dto.setPrice(product.getPrice());

        if (product.getStockInfo() != null) {
            dto.setQuantityInStock(product.getStockInfo().getQuantityInStock());
            dto.setWarehouseLocationCode(product.getStockInfo().getWarehouseLocationCode());
        }

        dto.setReviews(product.getReviews());

        return dto;
    }
}