package service;

import entity.OrderView;

import java.util.List;

public interface OrderService {
    List<OrderView> getAllOrders();
}
