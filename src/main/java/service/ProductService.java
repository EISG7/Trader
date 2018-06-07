package service;

import entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductOfBroker(String name);
}
