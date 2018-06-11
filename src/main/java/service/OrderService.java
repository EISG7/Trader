package service;

import entity.MarketDepthMessage;
import entity.OrderBlotter;
import entity.Orders;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<OrderBlotter> getAllOrders(String token);
    List<Orders> getOrdersNotFinished(String trader);
    boolean placeOrder(Map<String, Object> data);
    boolean putOrder(Orders o);
    boolean putMarketDepth(MarketDepthMessage msg);
}
