package controller;

import entity.Trader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TraderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Test {
    private final TraderService traderService;

    public Test(TraderService traderService) {
        this.traderService = traderService;
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
}
