package controller;

import entity.OrderView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    @ResponseBody
    public Map<String, Object> getTraders() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        List<OrderView> rows = orderService.getAllOrders();
        resultMap.put("rows", rows);
        return resultMap;
    }
}
