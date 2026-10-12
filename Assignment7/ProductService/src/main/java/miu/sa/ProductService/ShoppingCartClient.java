package miu.sa.ProductService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "shoppingcartservice")
public interface ShoppingCartClient {

    @PutMapping("/carts/items/product/{productNumber}/price")
    void updateProductPrice(
            @PathVariable String productNumber,
            @RequestParam double price);
}