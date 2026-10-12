package miu.sa.ShopService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productservice")
public interface ProductClient {

    @GetMapping("/products/{productNumber}")
    ProductData getProduct(
            @PathVariable String productNumber);
}