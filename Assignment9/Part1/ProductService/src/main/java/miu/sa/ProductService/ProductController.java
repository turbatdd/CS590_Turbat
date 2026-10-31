package miu.sa.ProductService;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final StockClient stockClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public ProductController(StockClient stockClient, CircuitBreakerFactory circuitBreakerFactory) {
        this.stockClient = stockClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @GetMapping("/product/{productNumber}")
    public Product getProduct(@PathVariable int productNumber) {

        int stock = circuitBreakerFactory
                .create("stockService")
                .run(
                        () -> stockClient.getStock(productNumber),
                        throwable -> {
                            System.out.println(
                                    "StockService unavailable: " + throwable.getMessage()
                            );
                            return -1;
                        }
                );

        return new Product(
                productNumber,
                "Product " + productNumber,
                stock
        );
    }
}