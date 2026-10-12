package miu.sa.ShopService;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository
        extends MongoRepository<ShoppingCart, String> {
}