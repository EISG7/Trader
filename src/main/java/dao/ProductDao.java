package dao;

import entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product, Integer> {
    Iterable<Product> findByBroker(String broker);
}
