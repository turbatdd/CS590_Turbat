package esb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShippingController {

    @PostMapping("/orders")
    public ResponseEntity<?> receiveOrder(@RequestBody Order order) {
        return receiveOrder(order, "Shipping Application");
    }

    @PostMapping("/orders/next-day")
    public ResponseEntity<?> receiveNextDayOrder(@RequestBody Order order) {
        return receiveOrder(order, "Next-day shipping service");
    }

    @PostMapping("/orders/normal")
    public ResponseEntity<?> receiveNormalOrder(@RequestBody Order order) {
        return receiveOrder(order, "Normal shipping service");
    }

    @PostMapping("/orders/international")
    public ResponseEntity<?> receiveInternationalOrder(@RequestBody Order order) {
        return receiveOrder(order, "International shipping service");
    }

    private ResponseEntity<Order> receiveOrder(Order order, String shippingService) {
        System.out.println(shippingService + " receiving order: " + order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
}
