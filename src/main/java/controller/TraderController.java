package controller;

import com.google.gson.Gson;
import entity.Trader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TraderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TraderController {
    private final TraderService traderService;
    private final Gson gson;

    public TraderController(TraderService traderService, Gson gson) {
        this.traderService = traderService;
        this.gson = gson;
    }

    @GetMapping("/traders")
    @ResponseBody
    public Map<String, Object> getTraders() {
        Map<String, Object> resultMap = new HashMap<>();
        List<Trader> traders = traderService.getAllTraders();
        resultMap.put("success", true);
        resultMap.put("traders", traders);
        return resultMap;
    }

    @PostMapping("/token")
    @ResponseBody
    public Map<String, Object> login(@RequestBody String jsonStr) {
        Map<String, Object> resultMap = new HashMap<>();
        Trader trader = gson.fromJson(jsonStr, Trader.class);
        String token = traderService.login(trader.getName(), trader.getPassword());
        if (token != null) {
            resultMap.put("success", true);
            resultMap.put("token", token);
            return resultMap;
        }
        resultMap.put("success", false);
        return resultMap;
    }

    @PostMapping("/trader")
    @ResponseBody
    public Map<String, Object> register(@RequestBody String jsonStr) {
        Map<String, Object> resultMap = new HashMap<>();
        System.out.println(jsonStr);
        Trader trader = gson.fromJson(jsonStr, Trader.class);
        String token = traderService.register(trader.getName(), trader.getPassword());
        resultMap.put("success", true);
        resultMap.put("token", token);
        return resultMap;
    }

}
