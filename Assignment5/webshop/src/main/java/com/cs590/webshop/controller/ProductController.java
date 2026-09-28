package com.cs590.webshop.controller;
import com.cs590.webshop.dto.ProductDto;
import com.cs590.webshop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct(
            @RequestBody ProductDto product) {

        return service.addProduct(product);
    }

    @GetMapping("/{productNumber}")
    public ProductDto getProduct(
            @PathVariable String productNumber) {

        return service.getById(productNumber);
    }
}