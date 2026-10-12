package miu.sa.ShopService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class CartItem {
    private String productNumber;
    private String productName;
    private double price;
    private int quantity;

    public CartItem() {}

    public String getProductNumber() { return productNumber; }
    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() { return productName; }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @RestController
    @RequestMapping("/carts")
    public static class ShoppingCartController {

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

            public String getProductNumber() { return productNumber; }
            public void setProductNumber(String productNumber) {
                this.productNumber = productNumber;
            }

            public int getQuantity() { return quantity; }
            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }
        }

        @PostMapping("/{customerNumber}/items")
        public ShoppingCart addItem(
                @PathVariable String customerNumber,
                @RequestBody AddItemRequest request) {

            // Fetch authoritative product data through service discovery
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

        @GetMapping("/{customerNumber}")
        public ShoppingCart getCart(
                @PathVariable String customerNumber) {
            return repository.findById(customerNumber)
                    .orElseThrow(() ->
                            new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Cart not found"));
        }

        @PutMapping("/items/product/{productNumber}/price")
        public void updateProductPrice(
                @PathVariable String productNumber,
                @RequestParam double price) {

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
}