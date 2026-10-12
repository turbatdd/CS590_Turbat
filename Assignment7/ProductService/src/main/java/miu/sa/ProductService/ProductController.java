package miu.sa.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;
    private final ShoppingCartClient shoppingCartClient;

    public ProductController(
            ProductRepository repository,
            ShoppingCartClient shoppingCartClient) {
        this.repository = repository;
        this.shoppingCartClient = shoppingCartClient;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    @GetMapping("/{productNumber}")
    public Product getProduct(
            @PathVariable String productNumber) {
        return repository.findById(productNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PutMapping("/{productNumber}/price")
    public Product updatePrice(
            @PathVariable String productNumber,
            @RequestParam double price) {
        Product product = repository.findById(productNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Product not found"));

        product.setPrice(price);
        Product updated = repository.save(product);

        // Update matching items in ShoppingCartService
        shoppingCartClient.updateProductPrice(productNumber, price);

        return updated;
    }
}