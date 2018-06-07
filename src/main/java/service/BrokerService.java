package service;

import java.util.List;
import java.util.Map;

public interface BrokerService {
    List<Map.Entry<String, String>> getAllBrokers();
    boolean putBroker(String broker, String ip);
    boolean deleteBroker(String broker);
}
