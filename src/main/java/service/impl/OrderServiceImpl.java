package service.impl;

import dao.OrderDao;
import entity.OrderBlotter;
import entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import service.OrderService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<OrderBlotter> getAllOrders(String name) {
        try {
            List<Orders> orders = new ArrayList<>();
            Iterable<Orders> it = orderDao.findAllByStatus(true);
            it.forEach(orders::add);
            Collections.sort(orders);
            List<OrderBlotter> list = new ArrayList<>();
            for (Orders o : orders)
                list.add(new OrderBlotter(o, name));
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Orders> getOrdersNotFinished(String trader) {
        Iterable<Orders> it = orderDao.findAllByInitiatorNameAndStatus(trader, false);
        List<Orders> orders = new ArrayList<>();
        it.forEach(orders::add);
        return orders;
    }

    @Override
    public boolean placeOrder(Map<String, String> data) {
        try {
            Integer type = Integer.valueOf(data.get("type"));
            System.out.println(type);
            switch (type) {
                // TODO: Save order & Send Order to Broker Gateway
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
