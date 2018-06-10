package service.impl;

import com.google.gson.Gson;
import dao.OrderDao;
import dao.ProductDao;
import entity.OrderBlotter;
import entity.Orders;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import service.OrderService;
import ws.WebSocketClient;

import java.util.*;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    public WebSocketClient getWsClient() {
        return wsClient;
    }

    public void setWsClient(WebSocketClient wsClient) {
        this.wsClient = wsClient;
    }

    private WebSocketClient wsClient;

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
            String broker = data.get("broker");
            String name = data.get("name");
            String company = data.get("company");
            String code = data.get("product");
            Product pro = null;
            Iterable<Product> it = productDao.findByBroker(broker);
            List<Product> list = new ArrayList<>();
            it.forEach(list::add);
            for (Product p : list) {
                if (p.getCode().equals(code)) pro = p;
            }
            Orders o;
            String product = pro.getName();
            String period = pro.getPeriod();
            Gson gson = new Gson();
            Map<String, Object> request = new HashMap<>();
            request.put("msgType", "order");
            request.put("type", type);
            request.put("traderName", name);
            request.put("traderCompany", company);
            request.put("code", code);
            switch (type) {
                case 0:
                    o = new Orders(broker, code, product, period, Integer.valueOf(data.get("price")), Integer.valueOf(data.get("quantity")), name, company, false, null, null, null );
                    o.setType(0);
                    o.setStatus(false);
                    orderDao.save(o);
                    request.put("amount", Integer.valueOf(data.get("quantity")));
                    request.put("price", Integer.valueOf(data.get("price")));
                    break;
                case 1:
                    o = new Orders(broker, code, product, period, 0, Integer.valueOf(data.get("quantity")), name, company, true, null, null, null );
                    o.setStatus(false);
                    o.setType(1);
                    orderDao.save(o);
                    request.put("amount", Integer.valueOf(data.get("quantity")));
                    break;
                case 4:
                    request.put("amount", Integer.valueOf(data.get("quantity")));
                    request.put("price", Integer.valueOf(data.get("price")));
                    break;
                default:
                    o = new Orders(broker, code, product, period, 0, Integer.valueOf(data.get("quantity")), name, company, true, null, null, null );
                    o.setStatus(false);
                    o.setType(type);
                    orderDao.save(o);
                    request.put("amount", Integer.valueOf(data.get("quantity")));
                    request.put("price", Integer.valueOf(data.get("price")));
            }
            wsClient.send(broker, gson.toJson(request));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
