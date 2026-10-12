package miu.sa.ShopService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.MongoRepository;

@SpringBootApplication
@EnableFeignClients

public class ShopServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopServiceApplication.class, args);
	}

	public static interface ShoppingCartRepository
			extends MongoRepository<ShoppingCart, String> {
	}
}
