package miu.sa.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final StockClient stockClient;

    public ProductController(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    @GetMapping("/product/{productNumber}")
    public Product getProduct(@PathVariable int productNumber) {

        int stock = stockClient.getStock(productNumber);

        return new Product(
                productNumber,
                "Product " + productNumber,
                stock
        );
    }
}