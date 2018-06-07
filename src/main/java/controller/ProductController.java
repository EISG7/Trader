package controller;

import entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{broker}")
    @ResponseBody
    public Map<String, Object> getTraders(@PathVariable("broker") String broker) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Product> productList = productService.getProductOfBroker(broker);
        resultMap.put("success", true);
        resultMap.put("product", productList);
        return resultMap;
    }
}
