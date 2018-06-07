package service.impl;

import dao.OrderDao;
import entity.Orders;
import entity.OrderView;
import org.springframework.beans.factory.annotation.Autowired;
import service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<OrderView> getAllOrders() {
        Iterable<Orders> it = orderDao.findAll();
        List<Orders> orders = new ArrayList<>();
        it.forEach(orders::add);
        List<OrderView> list = new ArrayList<>();
        for (Orders o : orders)
            list.add(new OrderView(o));
        return list;
    }
}
