package service;

import entity.Trader;

import java.util.List;
import java.util.Map;

public interface TraderService {

    List<Trader> getAllTraders();
    Map<String, Object> login(String name, String password);
    Map<String, Object> register(Trader trader);

    boolean checkToken(String token);
}
