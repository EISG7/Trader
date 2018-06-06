package service;

import entity.Trader;

import java.util.List;

public interface TraderService {

    List<Trader> getAllTraders();
    String login(String name, String password);
    String register(String name, String password);
}
