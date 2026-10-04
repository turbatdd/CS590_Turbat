package esb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class ShippingActivator {
	@Autowired
	RestTemplate restTemplate;

	public void shipNextDay(Order order) {
		ship(order, "Next-day shipping", "next-day");
	}

	public void shipNormally(Order order) {
		ship(order, "Normal shipping", "normal");
	}

	public void shipInternationally(Order order) {
		ship(order, "International shipping", "international");
	}

	private void ship(Order order, String shippingMethod, String endpoint) {
		System.out.println(shippingMethod + ": " + order);
		restTemplate.postForLocation("http://localhost:8082/orders/" + endpoint, order);
	}
}
