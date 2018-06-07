package service.impl;

import service.BrokerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrokerServiceImpl implements BrokerService {

    private Map<String, String> brokers;

    public Map<String, String> getBrokers() {
        return brokers;
    }

    public void setBrokers(Map<String, String> brokers) {
        this.brokers = brokers;
    }

    @Override
    public List<Map.Entry<String, String>> getAllBrokers() {
        return new ArrayList<>(brokers.entrySet());
    }

    @Override
    public boolean putBroker(String broker, String ip) {
        brokers.put(broker, ip);
        return true;
    }

    @Override
    public boolean deleteBroker(String broker) {
        if (broker.contains(broker)) {
            brokers.remove(broker);
            return true;
        }
        return false;
    }
}
