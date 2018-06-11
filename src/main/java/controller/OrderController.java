package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.MarketDepthMessage;
import entity.OrderBlotter;
import entity.Orders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.OrderService;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final Gson gson;

    public OrderController(OrderService orderService, Gson gson) {
        this.orderService = orderService;
        this.gson = gson;
    }

    @GetMapping("/orders/{trader}")
    @ResponseBody
    public Map<String, Object> getOrders(@PathVariable("trader") String trader) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        List<OrderBlotter> rows = orderService.getAllOrders(trader);
        resultMap.put("rows", rows);
        return resultMap;
    }

    @GetMapping("/ordersNotFinished/{trader}")
    @ResponseBody
    public Map<String, Object> getTraders(@PathVariable("trader") String trader) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        List<Orders> rows = orderService.getOrdersNotFinished(trader);
        resultMap.put("orders", rows);
        return resultMap;
    }

    @PostMapping("/orders")
    @ResponseBody
    public Map<String, Object> placeOrder(@RequestBody String jsonStr) {
        Map<String, Object> resultMap = new HashMap<>();
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> map = gson.fromJson(jsonStr, type);
        resultMap.put("success", orderService.placeOrder(map));
        return resultMap;
    }

    @PostMapping("/orderProcess")
    @ResponseBody
    public Map<String, Object> orderProcess(@RequestBody String jsonStr) {
        Map<String, Object> resultMap = new HashMap<>();
        Orders o = gson.fromJson(jsonStr, Orders.class);
        resultMap.put("success", orderService.putOrder(o));
        return resultMap;
    }

    @PostMapping("/marketDepth")
    @ResponseBody
    public Map<String, Object> marketDepth(@RequestBody String jsonStr) {
        Map<String, Object> resultMap = new HashMap<>();
        MarketDepthMessage mmsg = gson.fromJson(jsonStr, MarketDepthMessage.class);
        resultMap.put("success", orderService.putMarketDepth(mmsg));
        return resultMap;
    }

}
