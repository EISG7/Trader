package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BrokerService;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BrokerController {

    private final BrokerService brokerService;
    private final Gson gson;

    public BrokerController(BrokerService brokerService, Gson gson) {
        this.brokerService = brokerService;
        this.gson = gson;
    }

    @GetMapping("/brokers")
    @ResponseBody
    public Map<String, Object> getBrokers() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        resultMap.put("brokers", brokerService.getAllBrokers());
        return resultMap;
    }

    @PostMapping("/broker")
    @ResponseBody
    public Map<String, Object> addBroker(@RequestBody String jsonStr) {
        Map<String, Object> resultMap = new HashMap<>();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> map = gson.fromJson(jsonStr, type);
        String broker = map.get("name");
        String ip = map.get("ip");
        resultMap.put("success", brokerService.putBroker(broker, ip));
        return resultMap;
    }

    @DeleteMapping("/broker/{broker}")
    @ResponseBody
    public Map<String, Object> deleteBroker(@PathVariable("broker") String broker) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", brokerService.deleteBroker(broker));
        return resultMap;
    }
}
