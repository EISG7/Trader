package service.impl;

import dao.ProductDao;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProductOfBroker(String name) {
        Iterable<Product> it = productDao.findByBroker(name);
        List<Product> list = new ArrayList<>();
        it.forEach(list::add);
        return list;
    }
}
