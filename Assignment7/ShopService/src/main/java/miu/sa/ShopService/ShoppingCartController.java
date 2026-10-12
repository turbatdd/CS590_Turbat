package miu.sa.ShopService;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/carts")
public class ShoppingCartController {

    private final ShoppingCartRepository repository;
    private final ProductClient productClient;

    public ShoppingCartController(
            ShoppingCartRepository repository,
            ProductClient productClient) {
        this.repository = repository;
        this.productClient = productClient;
    }

    public static class AddItemRequest {
        private String productNumber;
        private int quantity;

        public AddItemRequest() {
        }

        public String getProductNumber() {
            return productNumber;
        }

        public void setProductNumber(String productNumber) {
            this.productNumber = productNumber;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    // Add a product to a customer's cart
    @PostMapping("/{customerNumber}/items")
    public ShoppingCart addItem(
            @PathVariable String customerNumber,
            @RequestBody AddItemRequest request) {

        if (request.getProductNumber() == null
                || request.getProductNumber().isBlank()
                || request.getQuantity() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Product number and positive quantity are required");
        }

        // Retrieve the actual product data through Consul/Feign
        ProductData product =
                productClient.getProduct(request.getProductNumber());

        ShoppingCart cart = repository.findById(customerNumber)
                .orElseGet(() -> {
                    ShoppingCart newCart = new ShoppingCart();
                    newCart.setCustomerNumber(customerNumber);
                    return newCart;
                });

        CartItem existing = cart.getItems().stream()
                .filter(item -> item.getProductNumber()
                        .equals(product.getProductNumber()))
                .findFirst()
                .orElse(null);

        if (existing == null) {
            CartItem item = new CartItem();
            item.setProductNumber(product.getProductNumber());
            item.setProductName(product.getName());
            item.setPrice(product.getPrice());
            item.setQuantity(request.getQuantity());

            cart.getItems().add(item);
        } else {
            existing.setQuantity(
                    existing.getQuantity() + request.getQuantity());
            existing.setProductName(product.getName());
            existing.setPrice(product.getPrice());
        }

        return repository.save(cart);
    }

    // Get a customer's shopping cart
    @GetMapping("/{customerNumber}")
    public ShoppingCart getCart(
            @PathVariable String customerNumber) {

        return repository.findById(customerNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Shopping cart not found"));
    }

    // Update the price in every cart containing this product
    @PutMapping("/items/product/{productNumber}/price")
    public void updateProductPrice(
            @PathVariable String productNumber,
            @RequestParam double price) {

        if (price < 0 || !Double.isFinite(price)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Price must be a valid non-negative number");
        }

        List<ShoppingCart> carts = repository.findAll();

        for (ShoppingCart cart : carts) {
            boolean changed = false;

            for (CartItem item : cart.getItems()) {
                if (item.getProductNumber().equals(productNumber)) {
                    item.setPrice(price);
                    changed = true;
                }
            }

            if (changed) {
                repository.save(cart);
            }
        }
    }
}
