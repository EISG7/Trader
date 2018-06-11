package service.impl;

import com.google.gson.Gson;
import dao.OrderDao;
import dao.ProductDao;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import service.OrderService;
import util.HttpUtil;
import ws.WebSocketServer;

import java.io.IOException;
import java.util.*;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private StringRedisTemplate redisTemplate;
    @Autowired
    private Gson gson;
    @Autowired
    private WebSocketServer wsServer;

    public Map<String, String> getBrokers() {
        return brokers;
    }

    public void setBrokers(Map<String, String> brokers) {
        this.brokers = brokers;
    }

    private Map<String, String> brokers;

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
    public boolean placeOrder(Map<String, Object> data) {
        Integer type = ((Double) data.get("type")).intValue();
        String broker = (String) data.get("broker");
        String code = (String) data.get("code");
        Product pro = null;
        Iterable<Product> it = productDao.findByBroker(broker);
        List<Product> list = new ArrayList<>();
        it.forEach(list::add);
        for (Product p : list) {
            if (p.getCode().equals(code)) pro = p;
        }
        data.put("type", type);
        data.put("quantity", ((Double) data.get("quantity")).intValue());
        data.put("product", pro.getName());
        data.put("period", pro.getPeriod());
        if (type == 2 || type == 3) {
            data.put("price", ((Double) data.get("price")).intValue());
        }
        System.out.print(new Gson().toJson(data));
        HttpUtil.sendPost(brokers.get(broker) + "/orders", new Gson().toJson(data));
        return true;
    }

    @Override
    public boolean putOrder(Orders o) {
        orderDao.save(o);
        return true;
    }

    @Override
    public boolean putMarketDepth(MarketDepthMessage msg) {
        List<MarketDepth> mdlist = msg.getMarketDepth();
        Collections.sort(mdlist);
        String list = gson.toJson(mdlist);
        redisTemplate.opsForValue().set(msg.genKey(), list);
        try {
            wsServer.send(msg.getBroker(), msg.getProduct(), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}
